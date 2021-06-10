package kz.accounting.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.accounting.jpa.model.BaseDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class UserBalanceDto extends BaseDto {

    @JsonProperty("balance_usd")
    private BigDecimal balanceUsd;

    @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;
}
