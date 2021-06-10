package kz.accounting.jpa.entity;

import kz.accounting.jpa.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "user_balance", schema = "user_data")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserBalance extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "balance_usd", nullable = false)
    private BigDecimal balanceUsd;

    @Column(name = "updated_at", columnDefinition = "timestamp with time zone")
    private OffsetDateTime updatedAt;
}