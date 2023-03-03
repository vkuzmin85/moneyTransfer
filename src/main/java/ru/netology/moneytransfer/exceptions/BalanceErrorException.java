package ru.netology.moneytransfer.exceptions;
public class BalanceErrorException extends RuntimeException {
    private final String message;

    public BalanceErrorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}