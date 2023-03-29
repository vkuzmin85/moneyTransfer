package ru.netology.moneytransfer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.moneytransfer.exceptions.InvalidDataException;
import ru.netology.moneytransfer.exceptions.TransactionErrorException;
import ru.netology.moneytransfer.model.Amount;
import ru.netology.moneytransfer.model.Card;
import ru.netology.moneytransfer.repository.CardsRepository;
import ru.netology.moneytransfer.repository.TransferRepository;
import ru.netology.moneytransfer.service.TransferServiceImpl;
import ru.netology.moneytransfer.util.CardChecking;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {
    @InjectMocks
    private TransferServiceImpl transferService;
    @Mock
    private CardsRepository cardsRepository;
    @Mock
    private TransferRepository transactionRepository;

    @Mock
    private CardChecking cardValidate;

    @Mock
    private Card validCard;

    @BeforeEach
    public void start() {
        validCard = new Card("1111111111111111", "01/24", "111", 10000);
        //cardsRepository = new CardsRepositoryImpl();
        cardValidate = new CardChecking(cardsRepository);
        transferService = new TransferServiceImpl(cardsRepository, transactionRepository);
    }

    @Test
    void getCardToTestOk() {
        when(cardsRepository.getCardByNumber("1111111111111111")).thenReturn(
                Optional.of(validCard)
        );
        var result = cardValidate.checkCardTo("1111111111111111");
        assertEquals(result, validCard);
    }

    @Test
    void getFromCardTestThrow() {
        var wrongCardNumber = "1212";
        when(cardsRepository.getCardByNumber(wrongCardNumber))
                .thenThrow(new InvalidDataException("Transaction failed. No card " + wrongCardNumber));
        assertThrows(InvalidDataException.class, () -> cardValidate.checkCardTo(wrongCardNumber));
    }

    @Test
    void getCardFromTestOk() {
        when(cardsRepository.getCardByWholeInfo("1111111111111111", "01/24", "111"))
                .thenReturn(Optional.of(validCard));
        var result = cardValidate.checkCardFrom("1111111111111111", "01/24", "111");
        assertEquals(result, validCard);
    }

    @Test
    void getCardFromTestThrow() {
        when(cardsRepository.getCardByWholeInfo("1111111111111111", "01/24", "111"))
                .thenThrow(new InvalidDataException("Transaction failed. No card " + "1111111111111111"));
        assertThrows(InvalidDataException.class, () -> cardValidate.checkCardFrom("1111111111111111", "01/24", "111"));
    }

    @Test
    void checkBalanceTestOk() {
        var card = new Card("2222222222222222", "02/22", "222", 300);
        var amount = new Amount("RUB", 200);
        amount.setCommissionPercent(1);
        var result = cardValidate.checkBalance(card, amount);
        assertEquals(result, true);
    }

    @Test
    void checkBalanceTestThrow() {
        var amount = new Amount("RUB", 200000000);
        amount.setCommissionPercent(1);
        assertThrows(TransactionErrorException.class, () -> cardValidate.checkBalance(validCard, amount));
    }

    @AfterEach
    public void finished() {
        transferService = null;
        cardsRepository = null;
        transactionRepository = null;
        cardValidate = null;
    }

}
