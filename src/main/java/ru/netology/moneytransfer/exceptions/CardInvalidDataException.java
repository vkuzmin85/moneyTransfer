package ru.netology.moneytransfer.exceptions;

public class CardInvalidDataException extends RuntimeException {

    public CardInvalidDataException(String message) {
        super(message);
    }
}