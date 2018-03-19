package com.bsc.paymenttracker;

import java.math.BigDecimal;

public class ExchangeRate {
    private String sourceCurrency;
    private String goalCurrency;
    private BigDecimal amount;

    public ExchangeRate(String sourceCurrency, String goalCurrency, BigDecimal amount) {
        this.sourceCurrency = sourceCurrency;
        this.goalCurrency = goalCurrency;
        this.amount = amount;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getGoalCurrency() {
        return goalCurrency;
    }

    public void setGoalCurrency(String goalCurrency) {
        this.goalCurrency = goalCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
