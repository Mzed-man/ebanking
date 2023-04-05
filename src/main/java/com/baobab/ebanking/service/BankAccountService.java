package com.baobab.ebanking.service;

import com.baobab.ebanking.web.dto.BankAccountDto;
import com.baobab.ebanking.web.dto.BankAccountTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.baobab.ebanking.domain.BankAccount}.
 */
public interface BankAccountService {
    /**
     * Save a bankAccount.
     *
     * @param bankAccountDto the entity to save.
     * @return the persisted entity.
     */
    BankAccountDto save(BankAccountDto bankAccountDto);

    /**
     * Partially updates a bankAccount.
     *
     * @param bankAccountDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BankAccountDto> partialUpdate(BankAccountDto bankAccountDto);

    /**
     * Get all the bankAccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BankAccountDto> findAll(Pageable pageable);


    List<BankAccountDto> findAllList();

    /**
     * Get the "id" bankAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BankAccountDto> findOne(Long id);


    @Transactional(readOnly = true)
    Optional<BankAccountDto> findFirstByAccountId(String accountId);

    BankAccountDto touchBalance(BankAccountDto bankAccountDto, Long amount, String type);

}
