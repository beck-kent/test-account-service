package kz.accounting.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.accounting.jpa.model.BaseDto;
import kz.accounting.jpa.model.Currency;
import kz.accounting.jpa.enums.OperationType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class UserBalanceHistoryDto extends BaseDto {

    @JsonProperty("operation_type")
    private OperationType operationType;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
}
