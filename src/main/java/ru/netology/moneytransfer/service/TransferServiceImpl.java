package ru.netology.moneytransfer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.moneytransfer.exceptions.InvalidDataException;
import ru.netology.moneytransfer.exceptions.TransactionErrorException;
import ru.netology.moneytransfer.model.ConfirmOperation;
import ru.netology.moneytransfer.model.OperationId;
import ru.netology.moneytransfer.model.TransferInfo;
import ru.netology.moneytransfer.model.TransferRequest;
import ru.netology.moneytransfer.repository.CardsRepository;
import ru.netology.moneytransfer.repository.TransferRepository;
import ru.netology.moneytransfer.util.CardChecking;


@Service
@Slf4j
public class TransferServiceImpl implements TransferService {
    private final CardsRepository cardsRepository;
    private final TransferRepository transferRepository;
    private final CardChecking cardValidate;

    public TransferServiceImpl(CardsRepository cardsRepository, TransferRepository transactionRepository) {
        this.cardsRepository = cardsRepository;
        this.transferRepository = transactionRepository;
        cardValidate = new CardChecking(cardsRepository);
    }

    @Override
    public OperationId preTransfer(TransferRequest transfer) {
        //CardChecking cardValidate = new CardChecking(cardsRepository);
        log.info(transfer.toString());
        var cardFrom = cardValidate.checkCardFrom(transfer.getCardFromNumber(), transfer.getCardFromCVV(), transfer.getCardFromValidTill());
        var cardTo = cardValidate.checkCardTo(transfer.getCardToNumber());
        cardValidate.checkBalance(cardFrom, transfer.getAmount());
        return transferRepository.addTransferRepository(cardFrom, cardTo, transfer.getAmount());
    }

    @Override
    public ConfirmOperation confirmOfOperation(ConfirmOperation confirmOperation) {
        log.info(confirmOperation.toString());
        var transferOptional = transferRepository.getTransferByOperationId(confirmOperation.getOperationId());
        if (transferOptional.isEmpty()) {
            throw new InvalidDataException("Incorrect data");
        }
        TransferInfo transferInfo = transferOptional.get();

        //check code of operation
        if (!transferInfo.getCode().equals(confirmOperation.getCode())) {
            escapeTransfer(transferInfo);
            throw new TransactionErrorException("Incorrect confirmation code");
        }

        performTransfer(transferInfo);
        return confirmOperation;
    }

    @Override
    public void escapeTransfer(TransferInfo transferInfo) {
        var cardFrom = transferInfo.getCardFrom();
        cardFrom.getAmount().accumulateAndGet(
                transferInfo.getValue() + transferInfo.getCommission(),
                (prevValue, nextValue) -> prevValue + nextValue);
    }

    @Override
    public void performTransfer(TransferInfo transferInfo) {
        var cardFrom = transferInfo.getCardFrom();
        var cardTo = transferInfo.getCardTo();
        cardTo.getAmount().accumulateAndGet(transferInfo.getValue(), (prevValue, nextValue) -> prevValue + nextValue);
        log.info("Transaction from: " + cardFrom + " to " + cardTo + " was successful!");
    }

}
