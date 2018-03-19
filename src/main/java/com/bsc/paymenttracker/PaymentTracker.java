package com.bsc.paymenttracker;

public class PaymentTracker {

    private PaymentsOutputScheduler paymentsOutputScheduler;
    private InputReader inputReader;

    public PaymentTracker(String filepath) {
        Repository<String, ExchangeRate> exchangeRatesRepository = new ExchangeRateRepository();
        Repository<String, Payment> paymentsRepository = new PaymentRepository(filepath);
        this.paymentsOutputScheduler = new PaymentsOutputScheduler(paymentsRepository, exchangeRatesRepository);
        this.inputReader = new ConsoleInputReader(paymentsRepository);
    }

    public void start() {
        try {
            // start printer
            paymentsOutputScheduler.startScheduling();
            // start input reader
            inputReader.read();
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
        } finally {
            // stopScheduling
            paymentsOutputScheduler.stopScheduling();
        }
    }


}
