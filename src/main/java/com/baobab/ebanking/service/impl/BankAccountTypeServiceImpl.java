package com.baobab.ebanking.service.impl;

import com.baobab.ebanking.domain.BankAccount;
import com.baobab.ebanking.domain.BankAccountType;
import com.baobab.ebanking.repository.BankAccountTypeRepository;
import com.baobab.ebanking.service.BankAccountTypeService;
import com.baobab.ebanking.service.mapper.BankAccountTypeMapper;
import com.baobab.ebanking.web.dto.BankAccountDto;
import com.baobab.ebanking.web.dto.BankAccountTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountTypeServiceImpl implements BankAccountTypeService {

    private final Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final BankAccountTypeRepository bankAccountTypeRepository;
    @Autowired
    private final BankAccountTypeMapper bankAccountTypeMapper;

    public BankAccountTypeServiceImpl(BankAccountTypeRepository bankAccountTypeRepository, BankAccountTypeMapper bankAccountTypeMapper) {
        this.bankAccountTypeRepository = bankAccountTypeRepository;
        this.bankAccountTypeMapper = bankAccountTypeMapper;
    }

    @Override
    public BankAccountTypeDto save(BankAccountTypeDto bankAccountTypeDto) {
        log.debug("Request to save BankAccount : {}", bankAccountTypeDto);
        BankAccountType bankAccountType = bankAccountTypeMapper.toEntity(bankAccountTypeDto);
        bankAccountType = bankAccountTypeRepository.save(bankAccountType);
        return bankAccountTypeMapper.toDto(bankAccountType);
    }

    @Override
    public Optional<BankAccountTypeDto> partialUpdate(BankAccountTypeDto bankAccountTypeDto) {
        log.debug("Request to partially update bankAccount infos : {}", bankAccountTypeDto);

        return bankAccountTypeRepository
                .findById(bankAccountTypeDto.getId())
                .map(existingBankAccount -> {
                    bankAccountTypeMapper.partialUpdate(existingBankAccount, bankAccountTypeDto);

                    return existingBankAccount;
                })
                .map(bankAccountTypeRepository::save)
                .map(bankAccountTypeMapper::toDto);
    }

    @Override
    public Page<BankAccountTypeDto> findAll(Pageable pageable) {
        log.debug("Request to get all Territories");
        return bankAccountTypeRepository.findAll(pageable).map(bankAccountTypeMapper::toDto);
    }

    @Override
    public List<BankAccountTypeDto> findAllList() {
            return bankAccountTypeRepository.findAll().stream().map(bankAccountTypeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccountTypeDto> findOne(Long id) {
            log.debug("Request to get bankAccountType : {}", id);
            return bankAccountTypeRepository.findById(id).map(bankAccountTypeMapper::toDto);
    }
}
