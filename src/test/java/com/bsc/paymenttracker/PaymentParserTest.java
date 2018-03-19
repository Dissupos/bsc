package com.bsc.paymenttracker;


import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class PaymentParserTest {
    private Parser<Payment> parser;

    public PaymentParserTest() {
        parser = new PaymentParser();
    }

    @Test
    public void testParsePositiveValues() {
        Payment payment = parser.parse("USD 123");
        assertEquals("USD", payment.getCurrency());
        assertEquals(new BigDecimal("123"), payment.getAmount());
    }

    @Test
    public void testParseOppositeValues() {
        Payment payment = parser.parse("USD -789");
        assertEquals("USD", payment.getCurrency());
        assertEquals(new BigDecimal("-789"), payment.getAmount());
    }

    @Test
    public void testInputWithError() {
        try {
            parser.parse("asd 123");
            fail("parser must throw exception");
        } catch (ParserException e) {
            // good
        }

        try {
            parser.parse("UAD 123.12");
            fail("parser must throw exception");
        } catch (ParserException e) {
            // good
        }

        try {
            parser.parse("SADD 123");
            fail("parser must throw exception");
        } catch (ParserException e) {
            // good
        }

        try {
            parser.parse("123");
            fail("parser must throw exception");
        } catch (ParserException e) {
            // good
        }

        try {
            parser.parse("asd 123");
            fail("parser must throw exception");
        } catch (ParserException e) {
            // good
        }
    }


}
