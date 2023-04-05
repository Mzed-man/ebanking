package com.baobab.ebanking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "bank_account_type")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @NotNull
    @Column(name = "description", nullable = false, unique = true)
    private String description;


}
