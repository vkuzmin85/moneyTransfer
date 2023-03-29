package ru.netology.moneytransfer.model;

import java.util.UUID;

public class OperationId {
    private String operationId;

    public OperationId() {
        this.operationId = UUID.randomUUID().toString();
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public String toString() {
        return "OperationId{" +
                "operationId='" + operationId + '\'' +
                '}';
    }
}