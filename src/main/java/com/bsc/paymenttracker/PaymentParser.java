package com.bsc.paymenttracker;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentParser implements Parser<Payment> {


    public static final String PAYMENT_REGEX = "([A-Z]{3})\\s(-?\\d+)";

    @Override
    public Payment parse(String input) {

        Pattern pattern = Pattern.compile(PAYMENT_REGEX);
        Matcher matcher = pattern.matcher(input.trim());

        if (matcher.matches()) {
            String currency = matcher.group(1);
            BigDecimal amount = new BigDecimal(matcher.group(2));
            return new Payment(currency, amount);
        }
        throw new ParserException("Input string is not in correct format");
    }
}
