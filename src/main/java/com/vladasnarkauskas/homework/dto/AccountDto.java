package com.vladasnarkauskas.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
    private final Long userId;
    private final Long accountId;
    private final BigDecimal balance;
}
