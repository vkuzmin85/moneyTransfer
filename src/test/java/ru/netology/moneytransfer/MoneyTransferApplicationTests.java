package ru.netology.moneytransfer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.moneytransfer.model.Card;
import ru.netology.moneytransfer.repository.CardsRepositoryImpl;
import ru.netology.moneytransfer.repository.TransferRepositoryImpl;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferApplicationTests {

    public CardsRepositoryImpl cards;
    public TransferRepositoryImpl transactions;

    @Test
    void CardTest() {
        cards = new CardsRepositoryImpl();
        Optional<Card> card = cards.getCardByNumber("1111");
        Assertions.assertNull(card.orElse(null));
    }

    @Test
    void repostitoryTest() {
        transactions = new TransferRepositoryImpl();
        Assertions.assertNull(transactions.getTransferByOperationId("11111").orElse(null));
    }
}