package com.vladasnarkauskas.homework.service;

import com.vladasnarkauskas.homework.dao.Account;
import com.vladasnarkauskas.homework.dao.AccountHistory;
import com.vladasnarkauskas.homework.dto.AccountDto;
import com.vladasnarkauskas.homework.dto.AccountHistoryDto;
import com.vladasnarkauskas.homework.dto.TransactionDto;
import com.vladasnarkauskas.homework.exception.AccountServiceException;
import com.vladasnarkauskas.homework.repository.AccountHistoryRepository;
import com.vladasnarkauskas.homework.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountHistoryRepository accountHistoryRepository;

    public AccountServiceImpl(final AccountRepository accountRepository,
                              final AccountHistoryRepository accountHistoryRepository) {
        this.accountHistoryRepository = accountHistoryRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(final AccountDto accountDto) {
        final Account account = new Account();
        account.setBalance(new BigDecimal(0));
        account.setCustomerId(accountDto.getUserId());
        final Account savedAccount = accountRepository.save(account);
        return convertAccountToAccountDto(savedAccount);
    }

    @Override
    @Transactional
    public AccountDto setBalance(final TransactionDto transactionDto) throws AccountServiceException {
        final Account account = this.accountRepository
                .findByIdAndCustomerId(transactionDto.getAccountId(), transactionDto.getUserId())
                .orElseThrow(() -> new AccountServiceException(HttpStatus.NOT_FOUND, "Account not found"));

        if (account.getBalance().doubleValue() + transactionDto.getDelta() < 0) {
            throw new AccountServiceException(HttpStatus.CONFLICT, "Attempting to withdraw more funds than available");
        }

        double newAmount = account.getBalance().doubleValue() + transactionDto.getDelta();

        account.setBalance(
                new BigDecimal(newAmount).setScale(2, RoundingMode.HALF_UP)
        );

        final AccountHistory accountHistory = new AccountHistory();
        accountHistory.setAccount(account);
        accountHistory.setBalance(account.getBalance());
        accountHistory.setTransaction(transactionDto.getDelta());
        accountHistory.setDate(new Date());
        accountHistoryRepository.save(accountHistory);

        final Account savedAccount = accountRepository.save(account);
        return convertAccountToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getBalance(Long userId, Long accountId) {
        final Account account = this.accountRepository
                .findByIdAndCustomerId(accountId,userId)
                .orElseThrow(() -> new AccountServiceException(HttpStatus.NOT_FOUND, "Account not found"));
        return convertAccountToAccountDto(account);
    }

    @Override
    public List<AccountHistoryDto> getAccountTransactionHistory(Long accountId) {
        // this is suboptimal. we should get the 10 items from DB directly, but currently having a bug somewhere with
        // database table mapping which causes this all to fail. Could be done with paging and sorting in jpa
        return this.accountHistoryRepository
                .findByAccountId(accountId)
                .stream()
                .sorted(Comparator.comparing(AccountHistory::getDate).reversed())
                .limit(10)
                .map(this::convertToAccountHistoryDto)
                .collect(Collectors.toList());
    }

    private AccountDto convertAccountToAccountDto(final Account savedAccount) {
        final AccountDto savedAccountDto = new AccountDto(savedAccount.getCustomerId(),
                savedAccount.getId(), savedAccount.getBalance());
        return savedAccountDto;
    }

    private AccountHistoryDto convertToAccountHistoryDto(final AccountHistory accountHistory) {
        return new AccountHistoryDto(
                accountHistory.getDate(),
                BigDecimal.valueOf(accountHistory.getTransaction()).setScale(2, RoundingMode.HALF_UP)
        );
    }
}
