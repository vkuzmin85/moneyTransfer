package ru.netology.moneytransfer.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransfer.model.TransferInfo;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepository {
    private ConcurrentHashMap<String, TransferInfo> TransferRepository;

    public TransferRepository() {
        this.TransferRepository = new ConcurrentHashMap<>();
    }

    public Optional<TransferInfo> getTransferByOperationId(String operationId) {
        return Optional.ofNullable(TransferRepository.getOrDefault(operationId, null));
    }

    public void addTransfer(String operationId, TransferInfo transfer) {
        TransferRepository.put(operationId, transfer);
    }
}