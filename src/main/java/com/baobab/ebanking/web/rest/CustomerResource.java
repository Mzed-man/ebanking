package com.baobab.ebanking.web.rest;

import com.baobab.ebanking.service.CustomerService;
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
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Path("/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;
    private static final String ENTITY_NAME = "customer";
    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> getCustomers(){
        return customerService.findAllList();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCustomer(CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to register a Customer : {}", customerDTO.getName());
        if(customerDTO.getId() != null){
            throw new BadRequestException();
        }
        CustomerDTO result = customerService.save(customerDTO);
        return Response.created(new URI("/api/customers/" + result.getId())).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id")  Long id, CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to update Customer : {}, {}", id, customerDTO);
        if (customerDTO.getId() == null) {
            throw new BadRequestException();
        }
        if (!Objects.equals(id, customerDTO.getId())) {
            throw new BadRequestException();
        }

        if (!customerService.findOne(id).isPresent()) {
            throw new BadRequestException();
        }

        CustomerDTO result = customerService.save(customerDTO);

        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }
    
}
