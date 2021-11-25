package com.store.electronic.service;

import lombok.Getter;

@Getter
public class RegistrationException extends Exception{
    private final ErrorType errorType;

    public RegistrationException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public enum ErrorType{
        USERNAME_EXIST,
        USERNAME_NOT_VALID,
        PASSWORD_WEAK
    }
}
