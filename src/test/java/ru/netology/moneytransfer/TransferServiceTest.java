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
import ru.netology.moneytransfer.services.TransferService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {
    @InjectMocks
    private TransferService transferService;
    @Mock
    private CardsRepository cardsRepository;
    @Mock
    private TransferRepository transactionRepository;
    private Card validCard = new Card("1111111111111111", "01/21", "111", 10000);

    @BeforeEach
    public void start() {
        transferService = new TransferService(cardsRepository, transactionRepository);
    }

    @Test
    void getCardToTestOk() {
        when(cardsRepository.getCardByNumber("1111111111111111")).thenReturn(
                Optional.of(validCard)
        );
        var result = transferService.getCardTo("1111111111111111");
        assertEquals(result, validCard);
    }

    @Test
    void getFromCardTestThrow() {
        var wrongCardNumber = "1212";
        when(cardsRepository.getCardByNumber(wrongCardNumber))
                .thenThrow(new InvalidDataException("Transaction failed. No card " + wrongCardNumber));
        assertThrows(InvalidDataException.class, () -> transferService.getCardTo(wrongCardNumber));
    }

    @Test
    void getCardFromTestOk(){
        when(cardsRepository.getCardByWholeInfo("1111111111111111","01/24","10000"))
                .thenReturn(Optional.of(validCard));
        var result = transferService.getCardFrom("1111111111111111","01/24","10000");
        assertEquals(result, validCard);
    }

    @Test
    void getCardFromTestThrow(){
        when(cardsRepository.getCardByWholeInfo("1111111111111111","01/24","111" ))
                .thenThrow(new InvalidDataException("Transaction failed. No card " + "1111111111111111"));
        assertThrows(InvalidDataException.class, () -> transferService.getCardFrom("1111111111111111","01/24","111"));
    }

    @Test
    void checkBalanceTestOk(){
        var card = new Card("2222222222222222", "02/22", "222",300);
        var amount = new Amount("RUB",200);
        amount.setCommissionPercent(1);
        var result = transferService.checkBalance(card, amount);
        assertEquals(result,true);
    }

    @Test
    void checkBalanceTestThrow() {
        var amount = new Amount("RUB",200000000);
        amount.setCommissionPercent(1);
        assertThrows(TransactionErrorException.class, ()->transferService.checkBalance(validCard,amount));
    }

    @AfterEach
    public void finished() {
        transferService = null;
        cardsRepository = null;
        transactionRepository = null;
    }

}
