package com.baobab.ebanking.repository;

import com.baobab.ebanking.domain.BankAccount;
import com.baobab.ebanking.web.dto.BankAccountDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findFirstByAccountId(String accountID);
}