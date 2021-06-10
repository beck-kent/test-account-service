package kz.accounting.jpa.repository;

import kz.accounting.jpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
