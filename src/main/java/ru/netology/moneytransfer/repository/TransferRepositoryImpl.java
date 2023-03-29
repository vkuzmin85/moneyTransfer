package ru.netology.moneytransfer.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.netology.moneytransfer.model.Amount;
import ru.netology.moneytransfer.model.Card;
import ru.netology.moneytransfer.model.OperationId;
import ru.netology.moneytransfer.model.TransferInfo;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class TransferRepositoryImpl implements TransferRepository {
    private final ConcurrentHashMap<String, TransferInfo> transferRepository;

    public TransferRepositoryImpl() {
        this.transferRepository = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<TransferInfo> getTransferByOperationId(String operationId) {
        return Optional.ofNullable(transferRepository.getOrDefault(operationId, null));
    }

    private void addTransfer(String operationId, TransferInfo transfer) {
        transferRepository.put(operationId, transfer);
    }

    @Override
    public OperationId addTransferRepository(Card cardFrom, Card cardTo, Amount amount) {
        var operation = new OperationId();
        var transferInfo = new TransferInfo(
                cardFrom,
                cardTo,
                amount.getValue(),
                amount.getCommission(),
                "0000"
        );
        transferRepository.put(operation.getOperationId(), transferInfo);
        log.info("Operation id: " + operation.getOperationId());
        log.info("Waiting for confirmation!");
        return operation;
    }
}