package com.vladasnarkauskas.homework;

import com.vladasnarkauskas.homework.repository.AccountHistoryRepository;
import com.vladasnarkauskas.homework.repository.AccountRepository;
import com.vladasnarkauskas.homework.service.AccountService;
import com.vladasnarkauskas.homework.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountHistoryRepository accountHistoryRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl concreteRef;
    private AccountService ref;


    @BeforeEach
    public void setup() {
        ref = concreteRef;
    }

    @Test
    public void somemethodunittest() {
        // the mocks etc would go here.
    }
}
