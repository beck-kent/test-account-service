package kz.accounting.service;

import kz.accounting.jpa.dto.RequestUserBalanceHistoryDto;
import kz.accounting.jpa.enums.OperationType;
import kz.accounting.jpa.model.Currency;
import kz.accounting.jpa.repository.UserBalanceRepository;
import kz.accounting.model.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AcknowledgingConsumerAwareMessageListener;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class UserBalanceServiceTest extends AbstractTest {

    @Value("${kafka.save-balance-topic}")
    private String topic;

    @Autowired
    private UserBalanceService userBalanceService;

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    public KafkaTemplate<Object, Object> kafkaTemplate;

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    private CountDownLatch latch;

    private RequestUserBalanceHistoryDto getRequestUserBalanceHistory(OperationType operationType, BigDecimal amount, Currency currency) {
        RequestUserBalanceHistoryDto request = new RequestUserBalanceHistoryDto();
        request.setUserId(1L);
        request.setAmount(amount);
        request.setOperationType(operationType);
        request.setCurrency(currency);
        return request;
    }

    @Test
    void saveBalanceAddTest() {
        // given
        var request = getRequestUserBalanceHistory(OperationType.ADD, BigDecimal.valueOf(1400), Currency.USD);
        var userBalance = userBalanceRepository.findByUserId(request.getUserId()).orElse(null);
        var balanceUsd = userBalance.getBalanceUsd();

        // when
        userBalanceService.saveBalance(request);

        // then
        userBalance = userBalanceRepository.findByUserId(request.getUserId()).orElse(null);
        assertNotNull(userBalance);
        assertEquals(userBalance.getBalanceUsd().compareTo(balanceUsd.add(request.getAmount())), 0);
    }

    @Test
    void saveBalanceSubTest() {
        var requestAdd = getRequestUserBalanceHistory(OperationType.ADD, BigDecimal.valueOf(1400), Currency.USD);
        var requestSub = getRequestUserBalanceHistory(OperationType.SUB, BigDecimal.valueOf(1400), Currency.USD);

        var userBalance = userBalanceRepository.findByUserId(requestAdd.getUserId()).orElse(null);
        var balanceUsd = userBalance.getBalanceUsd();

        userBalanceService.saveBalance(requestAdd);
        userBalanceService.saveBalance(requestSub);

        // then
        userBalance = userBalanceRepository.findByUserId(requestAdd.getUserId()).orElse(null);
        assertNotNull(userBalance);
        assertEquals(userBalance.getBalanceUsd().compareTo(balanceUsd), 0);
    }

    @BeforeEach
    void setUp() {
        ConcurrentMessageListenerContainer<?, ?> container = (ConcurrentMessageListenerContainer<?, ?>) registry
                .getAllListenerContainers().stream().findFirst().get();
        container.stop();
        @SuppressWarnings("unchecked")
        AcknowledgingConsumerAwareMessageListener<String, String> messageListener = (AcknowledgingConsumerAwareMessageListener<String, String>) container
                .getContainerProperties().getMessageListener();
        latch = new CountDownLatch(1);
        container.getContainerProperties()
                .setMessageListener((AcknowledgingConsumerAwareMessageListener<String, String>) (data, acknowledgment, consumer) -> {
                    messageListener.onMessage(data, acknowledgment, consumer);
                    latch.countDown();
                });
        container.start(); // обязательно перезапускать контейнер
    }

    @Test
    @DirtiesContext
    void saveBalanceAddKafkaTest() throws InterruptedException, TimeoutException, ExecutionException {
        var request = getRequestUserBalanceHistory(OperationType.ADD, BigDecimal.valueOf(1200), Currency.USD);
        var userBalance = userBalanceRepository.findByUserId(request.getUserId()).orElse(null);
        var balanceUsd = userBalance.getBalanceUsd();

        kafkaTemplate.send(topic, request).get(5, TimeUnit.SECONDS);

        latch.await(5, TimeUnit.SECONDS);

        userBalance = userBalanceRepository.findByUserId(request.getUserId()).orElse(null);
        assertNotNull(userBalance);
        assertEquals(userBalance.getBalanceUsd().compareTo(balanceUsd.add(request.getAmount())), 0);
    }
}
