package com.taufik.challenge6.Exceptions;

public class OrderDetailNotFoundException extends IllegalArgumentException {
    public OrderDetailNotFoundException(String message) {
        super(message);
    }
}