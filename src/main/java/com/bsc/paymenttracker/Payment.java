package com.bsc.paymenttracker;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Payment {

    private String currency;

    private BigDecimal amount;

    public Payment(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void addAmount(BigDecimal newAmount) {
        this.amount = this.amount.add(newAmount);
    }

    @Override
    public String toString() {
        BigDecimal amountTemp = this.amount != null ? this.amount.setScale(0, RoundingMode.CEILING) : BigDecimal.ZERO;
        return this.currency + " " + amountTemp;
    }
}
