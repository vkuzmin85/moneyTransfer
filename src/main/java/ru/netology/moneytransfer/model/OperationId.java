package ru.netology.moneytransfer.model;

import java.util.UUID;

public class OperationId {
        public String getOperationId() {
        return operationId;
    }

    private String operationId;

    public OperationId() {
        this.operationId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "OperationId{" +
                "operationId='" + operationId + '\'' +
                '}';
    }
}