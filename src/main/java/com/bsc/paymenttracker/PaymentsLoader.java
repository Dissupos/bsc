package com.bsc.paymenttracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentsLoader {
    public List<Payment> readPaymentsFromFile(String filepath) {
        List<Payment> payments = new ArrayList<>();
        File file = new File(filepath);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File '" + filepath + "' not found!");
        }

        if (sc == null) {
            return payments;
        }

        PaymentParser paymentParser = new PaymentParser();
        while (sc.hasNextLine()) {
            Payment payment;
            try {
                payment = paymentParser.parse(sc.nextLine());
            } catch (ParserException e) {
                continue;
            }
            payments.add(payment);
        }

        return payments;
    }
}
