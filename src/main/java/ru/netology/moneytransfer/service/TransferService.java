package ru.netology.moneytransfer.service;

import ru.netology.moneytransfer.model.ConfirmOperation;
import ru.netology.moneytransfer.model.OperationId;
import ru.netology.moneytransfer.model.TransferInfo;
import ru.netology.moneytransfer.model.TransferRequest;

public interface TransferService {
    public OperationId preTransfer(TransferRequest transfer);

    public ConfirmOperation confirmOfOperation(ConfirmOperation confirmOperation);

    public void escapeTransfer(TransferInfo transferInfo);

    public void performTransfer(TransferInfo transferInfo);

}
