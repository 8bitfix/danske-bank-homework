package com.vladasnarkauskas.homework.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "ACCOUNT_HISTORY")
@Getter
@Setter
@SequenceGenerator(
        name = "accountHistoryIdGenerator",
        sequenceName = "HIBERNATE_SEQUENCE",
        allocationSize = 1
)
public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "accountHistoryIdGenerator")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;
    private Date date;
    private Double transaction;
    private BigDecimal balance;

}
