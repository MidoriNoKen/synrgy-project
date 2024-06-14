package com.taufik.challenge6.Exceptions;

public class UsernameExistedException extends IllegalArgumentException {
    public UsernameExistedException(String message) {
        super(message);
    }
}
