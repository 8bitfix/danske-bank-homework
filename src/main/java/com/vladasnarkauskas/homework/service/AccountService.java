package com.vladasnarkauskas.homework.service;

import com.vladasnarkauskas.homework.dto.AccountDto;
import com.vladasnarkauskas.homework.dto.AccountHistoryDto;
import com.vladasnarkauskas.homework.dto.TransactionDto;
import com.vladasnarkauskas.homework.exception.AccountServiceException;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto setBalance(TransactionDto transactionDto) throws AccountServiceException;
    AccountDto getBalance(Long userId, Long accountId);
    List<AccountHistoryDto> getAccountTransactionHistory(Long accountId);
}
