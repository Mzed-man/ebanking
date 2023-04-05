package com.baobab.ebanking.service.mapper;

import com.baobab.ebanking.domain.BankAccount;
import com.baobab.ebanking.domain.BankAccountType;
import com.baobab.ebanking.web.dto.BankAccountDto;
import com.baobab.ebanking.web.dto.BankAccountTypeDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper for the entity {@link BankAccount} and its DTO {@link BankAccountDto}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankAccountTypeMapper extends EntityMapper<BankAccountTypeDto, BankAccountType> {

}
