package kz.accounting.jpa.repository;

import kz.accounting.jpa.entity.UserBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceHistoryRepository extends JpaRepository<UserBalanceHistory, Long> {
}
