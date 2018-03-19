package com.bsc.paymenttracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentRepository implements Repository<String, Payment> {

    private final Map<String, Payment> payments;

    public PaymentRepository(String filepath) {
        this.payments = new ConcurrentHashMap<>();
        if (filepath != null) {
            initFromFile(filepath);
        }
    }

    private void initFromFile(String filepath) {
        PaymentsLoader paymentsLoader = new PaymentsLoader();
        List<Payment> loadedPayments = paymentsLoader.readPaymentsFromFile(filepath);

        if (loadedPayments.isEmpty()) {
            return;
        }
        System.out.println("Loaded " + loadedPayments.size() + " payments");
        this.addAllItems(loadedPayments);
    }

    public synchronized void addItem(Payment item) {
        String key = item.getCurrency();
        Payment payment;
        if (payments.containsKey(key)) {
            payment = payments.get(key);
            payment.addAmount(item.getAmount());
        } else {
            payment = item;
        }
        payments.put(key, payment);

    }

    public synchronized void addAllItems(List<Payment> items) {
        for (Payment item : items) {
            this.addItem(item);
        }
    }

    public synchronized void removeAllItems() {
        for (String key : payments.keySet()) {
            payments.remove(key);
        }
    }

    public synchronized void removeItem(Payment item) {
        payments.remove(item.getCurrency());
    }

    public synchronized List<Payment> getAllItems() {
        return new ArrayList<>(payments.values());
    }

    public synchronized Payment getItem(String key) {
        return payments.get(key);
    }

    public synchronized boolean isEmpty() {
        return payments.isEmpty();
    }
}
