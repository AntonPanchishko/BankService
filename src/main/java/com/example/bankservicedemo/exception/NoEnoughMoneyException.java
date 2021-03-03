package com.example.bankservicedemo.exception;

public class NoEnoughMoneyException extends RuntimeException {
    public NoEnoughMoneyException(String message) {
        super(message);
    }
}
