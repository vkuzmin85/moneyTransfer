package ru.netology.moneytransfer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.netology.moneytransfer.exceptions.CardInvalidDataException;
import ru.netology.moneytransfer.exceptions.InvalidDataException;
import ru.netology.moneytransfer.exceptions.TransactionErrorException;
import ru.netology.moneytransfer.model.*;
import ru.netology.moneytransfer.repository.CardsRepository;
import ru.netology.moneytransfer.repository.TransferRepository;

import java.util.Optional;


@Service
public class TransferService {
    private final Logger logger = LoggerFactory.getLogger(TransferService.class);
    private CardsRepository cardsRepository;
    private TransferRepository transferRepository;

    public TransferService(CardsRepository cardsRepository, TransferRepository transactionRepository) {
        this.cardsRepository = cardsRepository;
        this.transferRepository = transactionRepository;
    }

    public void escapeTransfer(TransferInfo transferInfo) {
        var cardFrom = transferInfo.getCardFrom();
        cardFrom.getAmount().accumulateAndGet(
                transferInfo.getValue() + transferInfo.getCommission(),
                (x, y) -> x + y);
    }

    public ConfirmOperation confirmOfOperation(ConfirmOperation confirmOperation) {
        logger.info(confirmOperation.toString());

        var transferOptional = transferRepository.getTransferByOperationId(confirmOperation.getOperationId());
        if (transferOptional.isEmpty()) {
            logger.info("Incorrect data");
            throw new InvalidDataException("Incorrect data");
        }
        TransferInfo transferInfo = transferOptional.get();

        //check coode of operation
        if (!transferInfo.getCode().equals(confirmOperation.getCode())) {
            logger.info(" Transaction failed, incorrect confirmation code");
            escapeTransfer(transferInfo);
            throw new TransactionErrorException("Incorrect confirmation code");
        }

        //perform transfer
        var cardFrom = transferInfo.getCardFrom();
        var cardTo = transferInfo.getCardTo();
        cardTo.getAmount().accumulateAndGet(transferInfo.getValue(), (x, y) -> x + y);
        logger.info("Transaction from: " + cardFrom + " to " + cardTo + " was successful!");

        return confirmOperation;
    }

    public boolean checkBalance(Card cardFrom, Amount amount) {
        cardFrom.getAmount().accumulateAndGet(
                amount.getValue() + amount.getCommission(),
                (x, y) ->
                {
                    if (x - y >= 0) {
                        return x - y;
                    } else {
                        logger.info(String.format("Not enough money on balance %s\n", cardFrom.getNumber()));
                        throw new TransactionErrorException("Not enough money " + cardFrom.getNumber());
                    }
                });
        return true;
    }

    public OperationId preTransfer(TransferRequest transfer) {
        logger.info(transfer.toString());

        var cardFrom = getCardFrom(transfer.getCardFromNumber(), transfer.getCardFromCVV(), transfer.getCardFromValidTill());
        var cardTo = getCardTo(transfer.getCardToNumber());
        checkBalance(cardFrom, transfer.getAmount());

        logger.info("Transfering is waiting for confirmation");
        return addTransferRepository(cardFrom, cardTo, transfer.getAmount());
    }

    public OperationId addTransferRepository(Card cardFrom, Card cardTo, Amount amount) {
        var operation = new OperationId();
        var transferInfo = new TransferInfo(
                cardFrom,
                cardTo,
                amount.getValue(),
                amount.getCommission(),
                "0000"
        );
        transferRepository.addTransfer(operation.getOperationId(), transferInfo);
        logger.info(operation.getOperationId());
        logger.info("Waiting for confirmation!");
        return operation;
    }

    public Card getCardFrom(String cardFromNumber, String cardFromCVV, String cardFromValidTill) {
        Optional<Card> optionalCard = cardsRepository.getCardByWholeInfo(cardFromNumber, cardFromCVV, cardFromValidTill);
        if (optionalCard.isEmpty()) {
            logger.info("Incorrect card number " + cardFromNumber);
            throw new CardInvalidDataException("Incorrect card number " + cardFromNumber);
        }
        return optionalCard.get();
    }

    public Card getCardTo(String cardNumber) {
        Optional<Card> optionalCard = cardsRepository.getCardByNumber(cardNumber);
        if (optionalCard.isEmpty()) {
            logger.info("Incorrect card number " + cardNumber);
            throw new CardInvalidDataException("Incorrect card number " + cardNumber);
        }
        return optionalCard.get();
    }
}
