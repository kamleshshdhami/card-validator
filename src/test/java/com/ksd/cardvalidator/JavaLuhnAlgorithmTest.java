package com.ksd.cardvalidator;

import com.ksd.cardvalidator.exception.CreditCardException;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaLuhnAlgorithmTest {
    public static void main(String args[]){

//        validate("12345678903555");
//        validate("012850003580200");
//        validate("5355220877111215");
//        validate("6011000400000000");
        System.out.println("********************");
        validate("122000000000003");
//        validate("34343434343434");
//        validate("5555555555554444");
//        validate("5019717010103742");
//        validate("36700102000000");
//        validate("3528000700000000");


    }

    private static void validate(String cardNumber){
//        validateCreditCardNumber(cardNumber);
//        isValidCreditCardNumberLuhn(cardNumber);
        validateVerificationDigit(cardNumber);
    }

    private static void validateCreditCardNumber(String str) {

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
        if (sum % 10 == 0) {
            System.out.println(str + " is a valid credit card number");
        } else {
            System.out.println(str + " is an invalid credit card number");
        }
    }

    private static void isValidCreditCardNumberLuhn(String creditCardNumber) {

        boolean validCreditCardNumber = LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber);
        if(validCreditCardNumber)
        {
            System.out.println(creditCardNumber+" is valid as per luhn algorithm");
        }
        else
        {
            System.out.println(creditCardNumber+" is not valid as per luhn algorithm");
        }

    }

    private static void validateVerificationDigit(final String number) throws CreditCardException {
        List<Integer> digits = Arrays.asList(number.replaceAll("\\s", "").split(""))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Integer oddDigitsSum = IntStream.range(0, digits.size()- 1 )
                .filter(i -> i % 2 != 0).map(i -> {
                    int doubledValue = 2 * digits.get(i);
                    int x1 = doubledValue / 10;
                    int x2 = doubledValue % 10;
                    return x1 + x2;
                }).sum();

        Integer evenDigitsSum = IntStream.range(0, digits.size() - 1)
                .filter(i -> i % 2 == 0)
                .map(i -> digits.get(i))
                .sum();

        Integer totalSum = oddDigitsSum + evenDigitsSum;

        Integer checkDigit = new Integer(10 - (totalSum % 10));

        if (totalSum % 10 == 0) {
            System.out.println(number+" is not valid as per validateVerificationDigit algorithm");
        }else{
            System.out.println(number+" is valid as per validateVerificationDigit algorithm");
        }
    }
}
