package com.taufik.challenge6.Exceptions;

public class MerchantNotFoundException extends IllegalArgumentException {
    public MerchantNotFoundException(String message) {
        super(message);
    }
}
