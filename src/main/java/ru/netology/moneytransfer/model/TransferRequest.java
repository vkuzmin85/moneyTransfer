package ru.netology.moneytransfer.model;

import java.util.Objects;

public class TransferRequest {

    private Amount amount;
    private String cardToNumber;
    private String code;
    private String cardFromNumber;
    private String cardFromCVV;
    private String cardFromValidTill;

    public String getCardToNumber() {
        return cardToNumber;
    }

    public TransferRequest() {
    }

    public TransferRequest(String cardFromNumber, String cardFromCVV, String cardFromValidTill, Amount amount, String cardToNumber) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromCVV = cardFromCVV;
        this.cardFromValidTill = cardFromValidTill;
        this.amount = amount;
        this.cardToNumber = cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromCVV='" + cardFromCVV + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", amount=" + amount +
                ", cardToNumber='" + cardToNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferRequest that = (TransferRequest) o;
        return Objects.equals(getCardFromNumber(), that.getCardFromNumber()) && Objects.equals(getCardFromCVV(), that.getCardFromCVV()) && Objects.equals(getCardFromValidTill(), that.getCardFromValidTill()) && Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getCardToNumber(), that.getCardToNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardFromNumber(), getCardFromCVV(), getCardFromValidTill(), getAmount(), getCardToNumber());
    }
}