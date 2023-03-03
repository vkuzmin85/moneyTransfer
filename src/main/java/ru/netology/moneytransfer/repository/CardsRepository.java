package ru.netology.moneytransfer.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransfer.model.Card;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardsRepository {
    private ConcurrentHashMap<String, Card> cardRepository = new ConcurrentHashMap<>();
    //private final int START_AMOUNT = 0;

    public CardsRepository() {
        int INITIAL_AMOUNT = 155;
        Card card = new Card("1111111111111111", "01/24", "111", 10000);
        cardRepository.put(card.getNumber(), card);
        card = new Card("2222222222222222", "02/24", "222", 20000);
        cardRepository.put(card.getNumber(), card);
        card = new Card("3333", "03/24", "333", 0);
        cardRepository.put(card.getNumber(), card);
    }

    public Optional<Card> getCardByNumber(String cardNumber) {
        return Optional.ofNullable(cardRepository.getOrDefault(cardNumber, null));
    }

    public Optional<Card> getCardByWholeInfo(String cardNumber, String cardValid, String cardCVV) {
        Optional<Card> optionalCard = getCardByNumber(cardNumber);
        if (optionalCard.isPresent()) {
            Card card = new Card(cardNumber, cardValid, cardCVV, 0);
            optionalCard.filter(x -> x.equals(card));
        }
        return optionalCard;
    }
}