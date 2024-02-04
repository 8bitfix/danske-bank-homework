package com.vladasnarkauskas.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AccountHistoryDto {
    private final Date date;
    private final BigDecimal delta;
}
