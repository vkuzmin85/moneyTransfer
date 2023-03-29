package ru.netology.moneytransfer.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.moneytransfer.exceptions.CardInvalidDataException;
import ru.netology.moneytransfer.exceptions.TransactionErrorException;
import ru.netology.moneytransfer.model.Amount;
import ru.netology.moneytransfer.model.Card;
import ru.netology.moneytransfer.repository.CardsRepository;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class CardChecking {
    private CardsRepository cardsRepository;

    public Card checkCardFrom(String cardFromNumber, String cardFromCVV, String cardFromValidTill) {

        Optional<Card> optionalCard = cardsRepository.getCardByWholeInfo(cardFromNumber, cardFromCVV, cardFromValidTill);
        if (optionalCard.isEmpty()) {
            throw new CardInvalidDataException("Incorrect card number " + cardFromNumber);
        }
        return optionalCard.get();
    }

    public Card checkCardTo(String cardNumber) {
        Optional<Card> optionalCard = cardsRepository.getCardByNumber(cardNumber);
        if (optionalCard.isEmpty()) {
            throw new CardInvalidDataException("Incorrect card number " + cardNumber);
        }
        return optionalCard.get();
    }

    public boolean checkBalance(Card cardFrom, Amount amount) {
        cardFrom.getAmount().accumulateAndGet(
                amount.getValue() + amount.getCommission(),
                (prevValue, nextValue) ->
                {
                    if (prevValue - nextValue >= 0) {
                        return prevValue - nextValue;
                    } else {
                        throw new TransactionErrorException("Not enough money " + cardFrom.getNumber());
                    }
                });
        return true;
    }
}
