package com.ksd.cardvalidator.model;

import java.util.Date;

public class CreditCardResponseDTO {
    private String number;
    private boolean valid;
    private String message;

    private Date date;



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CreditCardResponseDTO(String number, boolean valid, String message) {
        this.number = number;
        this.valid = valid;
        this.message = message;
        this.date = new Date();
    }
}
