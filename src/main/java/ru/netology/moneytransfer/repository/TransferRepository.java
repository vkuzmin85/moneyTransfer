package ru.netology.moneytransfer.repository;

import ru.netology.moneytransfer.model.Amount;
import ru.netology.moneytransfer.model.Card;
import ru.netology.moneytransfer.model.OperationId;
import ru.netology.moneytransfer.model.TransferInfo;

import java.util.Optional;

public interface TransferRepository {
    public Optional<TransferInfo> getTransferByOperationId(String operationId);

    public OperationId addTransferRepository(Card cardFrom, Card cardTo, Amount amount);
}
