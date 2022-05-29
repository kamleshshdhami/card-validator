package com.ksd.cardvalidator.model;

public class CreditCard {
    private String number;
    private String expiration;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public CreditCard(String number, String expiration) {
        this.number = number;
        this.expiration = expiration;
    }
}
