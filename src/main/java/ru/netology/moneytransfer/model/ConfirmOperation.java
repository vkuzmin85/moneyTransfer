package ru.netology.moneytransfer.model;

public class ConfirmOperation {
    private String operationId;
    private String code;

    public ConfirmOperation(String operationId) {
        this.operationId = operationId;
    }

    public ConfirmOperation(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ConfirmOperation{" +
                "operationId=" + operationId +
                ", code=" + code +
                '}';
    }
}