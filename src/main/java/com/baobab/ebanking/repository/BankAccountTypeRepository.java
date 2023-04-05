package com.baobab.ebanking.repository;

import com.baobab.ebanking.domain.BankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, Long> {

}