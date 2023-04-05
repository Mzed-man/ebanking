package com.baobab.ebanking.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountTypeDto implements Serializable {
    private Long id;
    private String type;
    private String description;
}
