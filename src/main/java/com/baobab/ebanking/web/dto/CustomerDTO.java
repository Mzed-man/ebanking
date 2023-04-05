package com.baobab.ebanking.web.dto;

import com.baobab.ebanking.domain.Customer;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link Customer} entity.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String surname;

    private String msisdn;

    private String email;
}
