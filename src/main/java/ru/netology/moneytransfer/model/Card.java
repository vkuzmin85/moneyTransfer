package ru.netology.moneytransfer.model;
import java.util.concurrent.atomic.AtomicInteger;

public class Card {
    private String number;
    private String valid;
    private String cvv;
    private AtomicInteger amount;

    public  Card(String number, String valid, String cvv, int amount ){
        this.number = number;
        this.valid = valid;
        this.cvv = cvv;
        this.amount =  new AtomicInteger(amount);
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public AtomicInteger getAmount() {
        return amount;
    }

    public void setAmount(AtomicInteger amount) {
        this.amount = amount;
    }
}