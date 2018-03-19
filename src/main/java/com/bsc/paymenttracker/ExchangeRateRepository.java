package com.bsc.paymenttracker;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ExchangeRateRepository implements Repository<String, ExchangeRate> {

    public static final String DEFAULT_TARGET_CURRENCY = "USD";
    private String targetCurrency;
    private final Map<String, ExchangeRate> exchangeRates;

    public ExchangeRateRepository() {
        this.exchangeRates = new ConcurrentHashMap<>();
        this.targetCurrency = DEFAULT_TARGET_CURRENCY;
        init();
    }

    private void init() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "usd.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
            prop.load(input);

            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String currency = (String) e.nextElement();
                String rate = prop.getProperty(currency);
                this.addItem(new ExchangeRate(currency.toUpperCase(), DEFAULT_TARGET_CURRENCY, new BigDecimal(rate)));
            }

        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        }

    }

    public void addItem(ExchangeRate item) {
        exchangeRates.put(item.getSourceCurrency(), item);
    }

    public void addAllItems(List<ExchangeRate> items) {
        for (ExchangeRate item : items) {
            this.addItem(item);
        }
    }

    public void removeAllItems() {
        for (String key : exchangeRates.keySet()) {
            exchangeRates.remove(key);
        }
    }

    public void removeItem(ExchangeRate item) {
        exchangeRates.remove(item.getSourceCurrency());
    }

    public List<ExchangeRate> getAllItems() {
        return new ArrayList<>(exchangeRates.values());
    }

    public ExchangeRate getItem(String key) {
        return exchangeRates.get(key);
    }

    public boolean isEmpty() {
        return exchangeRates.isEmpty();
    }
}
