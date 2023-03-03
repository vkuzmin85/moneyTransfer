package ru.netology.moneytransfer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.moneytransfer.model.Card;
import ru.netology.moneytransfer.repository.CardsRepository;
import ru.netology.moneytransfer.repository.TransferRepository;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferApplicationTests {

    public CardsRepository cards;
    public TransferRepository transactions;

    //public ConfirmService confirmService;

    @Test
    void CardTest() {
        cards = new CardsRepository();
        Optional<Card> card = cards.getCardByNumber("1111");
        Assertions.assertNull(card.orElse(null));
    }

    @Test
    void repostitoryTest() {
        transactions = new TransferRepository();
        Assertions.assertNull(transactions.getTransferByOperationId("11111").orElse(null));
    }
}