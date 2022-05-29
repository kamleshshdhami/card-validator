package com.ksd.cardvalidator.exception;

public class CreditCardException extends RuntimeException {
    private static final long serialVersionUID = -9096652951123464901L;

    public CreditCardException(final String e) {
        super(e);
    }
}
