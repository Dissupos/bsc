package com.bsc.paymenttracker;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {

    private Repository<String, Payment> paymentsHolder;
    private Parser<Payment> paymentParser = new PaymentParser();

    public ConsoleInputReader(Repository<String, Payment> paymentsHolder) {
        this.paymentsHolder = paymentsHolder;
    }

    public void read() {
        Scanner in = new Scanner(System.in);
        String command;
        printUsage();

        while (!QUIT_COMMAND.equalsIgnoreCase(command = in.nextLine())) {
            try {
                Payment payment = paymentParser.parse(command);
                paymentsHolder.addItem(payment);
            } catch (ParserException pe) {
                System.out.println("Exception: " + pe.getMessage());
            }
        }
        in.close();
    }

    private void printUsage() {
        System.out.println("Welcome to the Payment Tracker!");
        System.out.println("You may add payments using format like this: \n" +
                "\t XXX 000 - (3 characters in upper case) whitespace (any number) \n" +
                "Examples:\n\t USD 123\n\t USD -156");
        System.out.println("Add payments:");
    }
}
