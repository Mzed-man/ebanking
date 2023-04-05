package com.baobab.ebanking.web.rest;

import com.baobab.ebanking.service.BankAccountTypeService;
import com.baobab.ebanking.web.dto.BankAccountDto;
import com.baobab.ebanking.web.dto.BankAccountTypeDto;
import com.baobab.ebanking.web.dto.CustomerDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Path("/bank-accounts-types")
public class BankAccountTypeResource {

    @Autowired
    private BankAccountTypeService bankAccountTypeService;
    private static final String ENTITY_NAME = "bankAccountType";
    private final Logger log = LoggerFactory.getLogger(BankAccountTypeResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BankAccountTypeDto> getBankAccountTypes(){
        return bankAccountTypeService.findAllList();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankAccountType(@PathParam("id") Long id){
        Optional<BankAccountTypeDto> bankAccountTypeDtoOptional = bankAccountTypeService.findOne(id);
        if(!bankAccountTypeDtoOptional.isPresent()){
            throw new NotFoundException();
        }
        return Response.ok().entity(bankAccountTypeDtoOptional.get()).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBankAccountType(BankAccountTypeDto bankAccountTypeDto) throws URISyntaxException {
        log.debug("REST request to create a Bank Account Type : {}", bankAccountTypeDto.getType());
        if(bankAccountTypeDto.getId() != null){
            throw new BadRequestException();
        }
        BankAccountTypeDto result = bankAccountTypeService.save(bankAccountTypeDto);
        return Response.created(new URI("/api/bank-accounts-types/" + result.getId())).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBankAccountType(@PathParam("id")  Long id, BankAccountTypeDto bankAccountTypeDto) throws URISyntaxException {
        log.debug("REST request to update Bank Account Type : {}, {}", id, bankAccountTypeDto);
        if (bankAccountTypeDto.getId() == null) {
            throw new BadRequestException();
        }
        if (!Objects.equals(id, bankAccountTypeDto.getId())) {
            throw new BadRequestException();
        }

        if (!bankAccountTypeService.findOne(id).isPresent()) {
            throw new BadRequestException();
        }

        BankAccountTypeDto result = bankAccountTypeService.save(bankAccountTypeDto);

        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }
    
}
