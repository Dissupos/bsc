package com.bsc.paymenttracker;

/**
 * @author Sarana
 */
public class Application {

    public static void main(String[] args) {
        // TODO Add logger like log4j
        String filepath = null;
        if (args != null && args.length > 0) {
            filepath = args[0];
        }
        PaymentTracker tracker = new PaymentTracker(filepath);
        tracker.start();
        System.exit(0);
    }


}
