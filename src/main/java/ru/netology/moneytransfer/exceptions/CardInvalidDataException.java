package ru.netology.moneytransfer.exceptions;

public class CardInvalidDataException extends RuntimeException {
    private final String message;

    public CardInvalidDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}