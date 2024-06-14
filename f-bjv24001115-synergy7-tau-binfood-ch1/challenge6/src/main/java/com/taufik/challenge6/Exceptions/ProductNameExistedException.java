package com.taufik.challenge6.Exceptions;

public class ProductNameExistedException extends IllegalArgumentException {
    public ProductNameExistedException(String message) {
        super(message);
    }
}
