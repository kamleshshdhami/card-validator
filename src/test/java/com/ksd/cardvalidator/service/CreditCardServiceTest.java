package com.ksd.cardvalidator.service;

import com.ksd.cardvalidator.model.CreditCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreditCardServiceTest {

    @Autowired
    CreditCardService creditCardService;

    CreditCard creditCard;

    @BeforeAll
    void setUp() {
        creditCard = new CreditCard("5019717010103742","04/23");
    }

    @Test
    void testValidate() {
        CreditCard creditCard = new CreditCard("5019717010103742","04/23");
        creditCardService.validate(creditCard);



    }
}
