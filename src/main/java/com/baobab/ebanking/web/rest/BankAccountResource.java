package com.baobab.ebanking.web.rest;

import com.baobab.ebanking.service.BankAccountService;
import com.baobab.ebanking.web.dto.BankAccountDto;
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
@Path("/bank-accounts")
public class BankAccountResource {

    @Autowired
    private BankAccountService bankAccountService;
    private static final String ENTITY_NAME = "bank-account";
    private final Logger log = LoggerFactory.getLogger(BankAccountResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BankAccountDto> getBankAccounts(){
        return bankAccountService.findAllList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankAccount(@PathParam("id")  Long id){
        Optional<BankAccountDto> bankAccountDtoOptional = bankAccountService.findOne(id);
        if(!bankAccountDtoOptional.isPresent()){
            throw new NotFoundException();
        }
        return Response.ok().entity(bankAccountDtoOptional.get()).build();

    }

    @GET
    @Path("/{id}/balance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankAccountBalance(@PathParam("id")  Long id){
        Optional<BankAccountDto> bankAccountDtoOptional = bankAccountService.findOne(id);
        if(!bankAccountDtoOptional.isPresent()){
            throw new NotFoundException();
        }
        return Response.ok(bankAccountDtoOptional.get().getBalance()).build();

    }

    @GET
    @Path("/{id}/deposit/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(@PathParam("id")  Long id, @PathParam("amount")  Long amount){
        Optional<BankAccountDto> bankAccountDtoOptional = bankAccountService.findOne(id);
        if(!bankAccountDtoOptional.isPresent()){
            throw new NotFoundException();
        }
        BankAccountDto bankAccountDto = bankAccountService.touchBalance(bankAccountDtoOptional.get(), amount, "deposit");
        return Response.ok().entity(bankAccountDto).build();
    }

    @GET
    @Path("/{id}/withdraw/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response withdraw(@PathParam("id")  Long id, @PathParam("amount")  Long amount){
        Optional<BankAccountDto> bankAccountDtoOptional = bankAccountService.findOne(id);
        if(!bankAccountDtoOptional.isPresent()){
            throw new NotFoundException();
        }
        BankAccountDto bankAccountDto = bankAccountService.touchBalance(bankAccountDtoOptional.get(), amount, "withdraw");
        return Response.ok().entity(bankAccountDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBankAccount(BankAccountDto bankAccountDto) throws URISyntaxException {
        log.debug("REST request to create a bank account : {}", bankAccountDto.getAccountId());
        if(bankAccountDto.getId() != null){
            throw new BadRequestException();
        }
        if(bankAccountService.findFirstByAccountId(bankAccountDto.getAccountId()).isPresent()){
            throw new BadRequestException();
        }
        BankAccountDto result = bankAccountService.save(bankAccountDto);
        return Response.created(new URI("/api/bank-accounts/" + result.getId())).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBankAccount(@PathParam("id")  Long id, BankAccountDto bankAccountDto) throws URISyntaxException {
        log.debug("REST request to update Bank Account : {}, {}", id, bankAccountDto);
        if (bankAccountDto.getId() == null) {
            throw new BadRequestException();
        }
        if (!Objects.equals(id, bankAccountDto.getId())) {
            throw new BadRequestException();
        }

        if (!bankAccountService.findOne(id).isPresent()) {
            throw new BadRequestException();
        }

        BankAccountDto result = bankAccountService.save(bankAccountDto);

        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }
    
}
