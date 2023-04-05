package com.baobab.ebanking.service.impl;

import com.baobab.ebanking.domain.BankAccount;
import com.baobab.ebanking.repository.BankAccountRepository;
import com.baobab.ebanking.service.BankAccountService;
import com.baobab.ebanking.service.mapper.BankAccountMapper;
import com.baobab.ebanking.web.dto.BankAccountDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
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

/**
 * Service Implementation for managing {@link BankAccount}.
 */
@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final BankAccountRepository bankAccountRepository;
    @Autowired
    private final BankAccountMapper bankAccountMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public BankAccountDto save(BankAccountDto bankAccountDTO) {
        log.debug("Request to save BankAccount : {}", bankAccountDTO);
        BankAccount bankAccount = bankAccountMapper.toEntity(bankAccountDTO);
        bankAccount = bankAccountRepository.save(bankAccount);
        return bankAccountMapper.toDto(bankAccount);
    }

    @Override
    public Optional<BankAccountDto> partialUpdate(BankAccountDto bankAccountDTO) {
        log.debug("Request to partially update bankAccount infos : {}", bankAccountDTO);

        return bankAccountRepository
            .findById(bankAccountDTO.getId())
            .map(existingBankAccount -> {
                bankAccountMapper.partialUpdate(existingBankAccount, bankAccountDTO);

                return existingBankAccount;
            })
            .map(bankAccountRepository::save)
            .map(bankAccountMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BankAccountDto> findAll(Pageable pageable) {
        log.debug("Request to get all Territories");
        return bankAccountRepository.findAll(pageable).map(bankAccountMapper::toDto);
    }

    @Override
    public List<BankAccountDto> findAllList() {
        return bankAccountRepository.findAll().stream().map(bankAccountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BankAccountDto> findOne(Long id) {
        log.debug("Request to get bankAccount : {}", id);
        return bankAccountRepository.findById(id).map(bankAccountMapper::toDto);
    }

    @Override
    public BankAccountDto touchBalance(BankAccountDto bankAccountDto, Long amount, String type) {
        if(amount<0){
            throw new BadRequestException();
        }
        switch (type){
            case "deposit":
                amount = amount;
                break;
            case "withdraw":
                amount = -amount;
                break;
            default:
                amount = 0L;
        }
        bankAccountDto.setBalance(bankAccountDto.getBalance() + amount);
        return this.save(bankAccountDto);
    }

}
