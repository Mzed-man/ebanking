package com.baobab.ebanking.service.mapper;

import com.baobab.ebanking.domain.Customer;
import com.baobab.ebanking.web.dto.CustomerDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link com.baobab.ebanking.domain.Customer} and its DTO {@link com.baobab.ebanking.web.dto.CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

}
