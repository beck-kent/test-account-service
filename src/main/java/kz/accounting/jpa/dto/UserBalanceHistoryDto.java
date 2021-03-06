package kz.accounting.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.accounting.jpa.model.AbstractDto;
import kz.accounting.jpa.model.Currency;
import kz.accounting.jpa.enums.OperationType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserBalanceHistoryDto extends AbstractDto {

    @JsonProperty("operation_type")
    private OperationType operationType;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("currency")
    private Currency currency;

}
