package com.baobab.ebanking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "bank_account")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "account_id", nullable = false, unique = true)
    private String accountId;

    @Column(name = "balance")
    private Long balance;

    @OneToOne
    private BankAccountType bankAccountType;

    @ManyToOne
    private Customer owner;
}
