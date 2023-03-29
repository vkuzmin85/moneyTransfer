package ru.netology.moneytransfer.exceptions;

public class TransactionErrorException extends RuntimeException {
    private final String message;

    public TransactionErrorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}