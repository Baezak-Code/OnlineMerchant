package com.online.merchant.model.exception;

public class IdNotPresentException extends RuntimeException {

    public IdNotPresentException() {
        super("Please provide a valid ID when interacting with the transaction service.");
    }
}
