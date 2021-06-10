package kz.accounting.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.accounting.jpa.enums.OperationType;
import kz.accounting.jpa.model.Currency;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RequestUserBalanceHistoryDto {

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotNull
    @JsonProperty("operation_type")
    private OperationType operationType;

    @NotNull
    @JsonProperty("amount")
    private BigDecimal amount;

    @NotNull
    @JsonProperty("currency")
    private Currency currency;
}
