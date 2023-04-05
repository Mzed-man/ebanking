package com.baobab.ebanking.web.dto;

import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String accountId;
    private Long balance;
    private BankAccountTypeDto bankAccountType;
    private CustomerDTO owner;

}
