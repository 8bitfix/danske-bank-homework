package com.vladasnarkauskas.homework.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "ACCOUNT")
@Getter
@Setter
@SequenceGenerator(
        name = "accountIdGenerator",
        sequenceName = "HIBERNATE_SEQUENCE",
        allocationSize = 1
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "accountIdGenerator")
    private Long id;
    private Long customerId;
    private BigDecimal balance;

    @OneToMany(mappedBy="account", cascade = CascadeType.REMOVE)
    private List<AccountHistory> accountHistories;
}
