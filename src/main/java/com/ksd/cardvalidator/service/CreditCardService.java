package com.ksd.cardvalidator.service;

import com.ksd.cardvalidator.exception.CreditCardException;
import com.ksd.cardvalidator.model.CreditCard;

public interface CreditCardService {
    public void validate(final CreditCard card) throws CreditCardException;
    public void validateApache(final CreditCard card) throws CreditCardException;
}
