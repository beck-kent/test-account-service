package kz.accounting.service;

import kz.accounting.commons.domain.PageDto;
import kz.accounting.jpa.dto.RequestUserBalanceHistoryDto;

public interface UserBalanceService {

    PageDto history(int page, int length);

    void saveBalance(RequestUserBalanceHistoryDto request);
}
