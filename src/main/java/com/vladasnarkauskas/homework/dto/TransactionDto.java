package com.vladasnarkauskas.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {
    private final Long userId;
    private final Long accountId;
    private final Double delta;
}
