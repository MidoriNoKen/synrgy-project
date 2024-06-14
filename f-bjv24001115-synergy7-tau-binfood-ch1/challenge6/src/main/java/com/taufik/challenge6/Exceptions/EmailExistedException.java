package com.taufik.challenge6.Exceptions;

public class EmailExistedException extends IllegalArgumentException {
    public EmailExistedException(String message) {
        super(message);
    }
}
