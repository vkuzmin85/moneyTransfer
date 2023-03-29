package ru.netology.moneytransfer.repository;

import ru.netology.moneytransfer.model.Card;

import java.util.Optional;

public interface CardsRepository {
    public Optional<Card> getCardByNumber(String cardNumber);

    public Optional<Card> getCardByWholeInfo(String cardNumber, String cardValid, String cardCVV);
}
