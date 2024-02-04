package com.vladasnarkauskas.homework.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AccountServiceException extends RuntimeException {

    private final HttpStatus status;
    public AccountServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
