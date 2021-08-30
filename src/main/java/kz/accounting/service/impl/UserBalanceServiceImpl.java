package kz.accounting.service.impl;

import kz.accounting.commons.domain.PageDto;
import kz.accounting.commons.utils.Time;
import kz.accounting.commons.exception.UserBalanceNotFoundException;
import kz.accounting.commons.exception.UserNotFoundException;
import kz.accounting.jpa.dto.RequestUserBalanceHistoryDto;
import kz.accounting.jpa.dto.UserBalanceHistoryDto;
import kz.accounting.jpa.entity.UserBalance;
import kz.accounting.jpa.entity.UserBalanceHistory;
import kz.accounting.jpa.entity.Users;
import kz.accounting.jpa.mapper.UserBalanceHistoryMapper;
import kz.accounting.jpa.model.Currency;
import kz.accounting.jpa.repository.UserBalanceHistoryRepository;
import kz.accounting.jpa.repository.UserBalanceRepository;
import kz.accounting.jpa.repository.UsersRepository;
import kz.accounting.service.ConvertService;
import kz.accounting.service.UserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Transactional
@RequiredArgsConstructor
@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final UsersRepository usersRepository;
    private final UserBalanceHistoryRepository userBalanceHistoryRepository;
    private final UserBalanceRepository userBalanceRepository;
    private final UserBalanceHistoryMapper userBalanceHistoryMapper;
    private final ConvertService convertService;

    @Value("${kafka.save-balance-topic}")
    private String topic;

    @Override
    public PageDto history(int page, int length) {
        Pageable pageable = PageRequest.of(page, length);
        Page pageEntity = userBalanceHistoryRepository.findAll(pageable);
        List<UserBalanceHistoryDto> list = userBalanceHistoryMapper.listUserBalanceHistoryToListUserBalanceHistoryDto(pageEntity.getContent());

        return PageDto.<UserBalanceHistoryDto> builder()
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .content(list)
                .build();
    }

    @Override
    public void saveBalance(RequestUserBalanceHistoryDto request) {
        UserBalanceHistory history = saveBalanceHistory(request);

        Users user = usersRepository.findById(history.getUserId()).orElseThrow(() -> new UserNotFoundException());
        UserBalance userBalance = userBalanceRepository.findByUserId(user.getId()).orElseThrow(() -> new UserBalanceNotFoundException());

        switch (history.getOperationType()) {
            case ADD:
                addBalance(userBalance, history.getAmount(), history.getCurrency());
                break;
            case SUB:
                subtractBalance(userBalance, history.getAmount(), history.getCurrency());
                break;
        }
    }

    @Override
    public void saveBalanceKafka(RequestUserBalanceHistoryDto request) {
        kafkaTemplate.send(topic, request);
    }

    private UserBalanceHistory saveBalanceHistory(RequestUserBalanceHistoryDto request) {
        return userBalanceHistoryRepository.save(UserBalanceHistory.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .operationType(request.getOperationType())
                .currency(request.getCurrency())
                .createdAt(Time.currentOffsetDateTime())
                .build());
    }

    /**
     * Пополнение баланса
     * @param userBalance запись о балансе
     * @param amount сумма запроса
     * @param currency валюта запроса
     */
    private void addBalance(UserBalance userBalance, BigDecimal amount, Currency currency) {
        switch (currency) {
            case USD:
                userBalance.setBalanceUsd(userBalance.getBalanceUsd().add(amount));
                break;
        }

        userBalanceRepository.save(userBalance);
    }

    /**
     * Снятие с баланса
     * @param userBalance запись о балансе
     * @param amount сумма запроса
     * @param currency валюта запроса
     */
    private void subtractBalance(UserBalance userBalance, BigDecimal amount, Currency currency) {
        switch (currency) {
            case USD:
                userBalance.setBalanceUsd(userBalance.getBalanceUsd().subtract(amount));
                break;
        }

        userBalanceRepository.save(userBalance);
    }

    @KafkaListener(topics = "${kafka.save-balance-topic}")
    public void listen(RequestUserBalanceHistoryDto request) {
        saveBalance(request);
    }
}
