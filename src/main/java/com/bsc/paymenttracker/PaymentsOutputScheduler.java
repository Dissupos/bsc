package com.bsc.paymenttracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PaymentsOutputScheduler extends TimerTask {

    public static final long DELAY = 60;

    private final Repository<String, Payment> paymentsHolder;
    private final Repository<String, ExchangeRate> currencyRateHolder;

    public PaymentsOutputScheduler(Repository<String, Payment> paymentsHolder, Repository<String, ExchangeRate> currencyRateHolder) {
        this.paymentsHolder = paymentsHolder;
        this.currencyRateHolder = currencyRateHolder;
    }

    public void startScheduling() {
        Timer timer = new Timer();
        long delay = TimeUnit.SECONDS.toMillis(PaymentsOutputScheduler.DELAY);
        timer.schedule(this, delay, delay);
    }

    public void stopScheduling() {
        this.cancel();
    }

    public void run() {
        if (paymentsHolder.isEmpty()) {
            return;
        }
        System.out.println("Actual payments (" + new Date() + "):");
        List<Payment> payments = paymentsHolder.getAllItems();

        for (Payment payment : payments) {
            if (payment.getAmount().compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            StringBuilder outputString = new StringBuilder(payment.toString());
            if (currencyRateHolder != null && !currencyRateHolder.isEmpty()) {
                ExchangeRate exchangeRate = currencyRateHolder.getItem(payment.getCurrency());
                if (exchangeRate != null) {
                    outputString.append(" (").append(exchangeRate.getGoalCurrency()).append(" ")
                            .append(payment.getAmount().multiply(exchangeRate.getAmount()).setScale(2, RoundingMode.CEILING)).append(")");
                }
            }
            System.out.println(outputString.toString());
        }
    }
}
