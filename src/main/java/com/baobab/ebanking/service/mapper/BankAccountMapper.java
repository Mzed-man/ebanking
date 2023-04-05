package com.baobab.ebanking.service.mapper;

import com.baobab.ebanking.domain.BankAccount;
import com.baobab.ebanking.web.dto.BankAccountDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper for the entity {@link BankAccount} and its DTO {@link BankAccountDto}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankAccountMapper extends EntityMapper<BankAccountDto, com.baobab.ebanking.domain.BankAccount> {

}
