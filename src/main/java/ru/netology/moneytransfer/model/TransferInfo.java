package ru.netology.moneytransfer.model;

import java.util.Objects;

public class TransferInfo {
    private Card cardFrom;
    private Card cardTo;
    private int value;
    private int commission;
    private String code;

    public TransferInfo(Card cardFrom, Card cardTo, int value, int commission, String code) {
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.value = value;
        this.commission = commission;
        this.code = code;
    }

    public Card getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(Card cardFrom) {
        this.cardFrom = cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public void setCardTo(Card cardTo) {
        this.cardTo = cardTo;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferInfo transfer = (TransferInfo) o;
        return getValue() == transfer.getValue() && getCommission() == transfer.getCommission() && Objects.equals(getCardFrom(), transfer.getCardFrom()) && Objects.equals(getCardTo(), transfer.getCardTo()) && Objects.equals(getCode(), transfer.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardFrom(), getCardTo(), getValue(), getCommission(), getCode());
    }
}