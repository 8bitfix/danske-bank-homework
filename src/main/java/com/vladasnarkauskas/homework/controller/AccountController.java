package com.vladasnarkauskas.homework.controller;

import com.vladasnarkauskas.homework.dto.AccountDto;
import com.vladasnarkauskas.homework.dto.AccountHistoryDto;
import com.vladasnarkauskas.homework.dto.TransactionDto;
import com.vladasnarkauskas.homework.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/account")
public class AccountController {

    private static final String USERID_PATH_VAR = "userId";
    private static final String ACCOUNTID_PATH_VAR = "accountId";

    private final AccountService accountService;

    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        return this.accountService.createAccount(accountDto);
    }

    @PostMapping("/balance")
    public AccountDto setBalance(@RequestBody TransactionDto transactionDto) {
        return this.accountService.setBalance(transactionDto);
    }

    @GetMapping("/balance/{userId}/{accountId}")
    public AccountDto getBalance(@PathVariable(USERID_PATH_VAR) Long userId,
                                 @PathVariable(ACCOUNTID_PATH_VAR) Long accountId) {
        return this.accountService.getBalance(userId, accountId);
    }

    @GetMapping("/history/{userId}/{accountId}")
    public List<AccountHistoryDto> getAccountTransactionHistory(@PathVariable(ACCOUNTID_PATH_VAR) Long accountId) {
        return this.accountService.getAccountTransactionHistory(accountId);
    }

}
