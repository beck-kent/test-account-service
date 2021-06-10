package kz.accounting.service;

import kz.accounting.commons.domain.PageDto;
import kz.accounting.jpa.dto.RequestUserBalanceHistoryDto;
import kz.accounting.jpa.dto.UserBalanceDto;

public interface UserBalanceService {

    PageDto history(int page, int length);

    void saveBalance(RequestUserBalanceHistoryDto request);

    void saveBalanceKafka(RequestUserBalanceHistoryDto request);

    UserBalanceDto getUserBalanceDto(Long userId);
}
