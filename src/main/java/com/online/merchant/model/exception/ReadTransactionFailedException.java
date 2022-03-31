package com.online.merchant.model.exception;

public class ReadTransactionFailedException extends RuntimeException {

    public ReadTransactionFailedException(String message) {
        super(message);
    }
}
