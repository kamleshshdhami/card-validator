package com.ksd.cardvalidator.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.springframework.stereotype.Service;
import com.ksd.cardvalidator.exception.CreditCardException;
import com.ksd.cardvalidator.model.CreditCard;


@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService{
    public void validate(final CreditCard card) throws CreditCardException {
        this.validateCardNumber(card.getNumber());
        this.validateExpiryDate(card.getExpiration());
    }

    public void validateApache(final CreditCard card) throws CreditCardException{
        this.validateCardNumberApache(card.getNumber());
        this.validateExpiryDate(card.getExpiration());
    }


    /**
     * Validate the credit card expiry date.
     *
     * @param date
     * 			String, represents a credit card date.
     *
     * @throws CreditCardException
     * @throws
     */
    protected void validateExpiryDate(final String date) throws CreditCardException  {
        try {
            DateFormat informat= new SimpleDateFormat("MM/yy");
            DateFormat outformat= new SimpleDateFormat("MM/yyyy");

            String formattedDate = "1/" + outformat.format(informat.parse(date));

            DateTimeFormatter df = DateTimeFormatter.ofPattern("d/MM/yyyy");

            LocalDate cardDate = LocalDate.parse(formattedDate, df);
            LocalDate currentDate = LocalDate.now();

            if(cardDate.isBefore(currentDate)) {
                throw new CreditCardException("Invalid, date in the past : " + date);
            }
        } catch(DateTimeParseException | ParseException e) {
            throw new CreditCardException("Invalid date format: " + date);
        }
    }

    /**
     * Validate the last digit of the given card number against the Lunh formula.
     *
     * @param str,
     * 			String represents a card number
     *
     * @throws CreditCardException
     */
    private void validateCardNumber(final String str) throws CreditCardException {
        int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }

        if (sum % 10 != 0) {
            throw new CreditCardException("Invalid verification digit for number: " + str);
        }
    }


    private void validateCardNumberApache(final String creditCardNumber) throws CreditCardException {
        boolean validCreditCardNumber = LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber);
        if(!validCreditCardNumber)
        {
            throw new CreditCardException("Invalid verification digit for number: " + creditCardNumber);
        }
    }
}
