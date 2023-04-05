package com.baobab.ebanking.service;

import com.baobab.ebanking.web.dto.BankAccountTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.baobab.ebanking.domain.BankAccountType}.
 */
public interface BankAccountTypeService {
    /**
     * Save a bankAccount.
     *
     * @param bankAccountTypeDto the entity to save.
     * @return the persisted entity.
     */
    BankAccountTypeDto save(BankAccountTypeDto bankAccountTypeDto);

    /**
     * Partially updates a bankAccount.
     *
     * @param bankAccountTypeDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BankAccountTypeDto> partialUpdate(BankAccountTypeDto bankAccountTypeDto);

    /**
     * Get all the bankAccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BankAccountTypeDto> findAll(Pageable pageable);


    List<BankAccountTypeDto> findAllList();

    /**
     * Get the "id" bankAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BankAccountTypeDto> findOne(Long id);

}
