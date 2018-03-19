package com.bsc.paymenttracker;


import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class PaymentsLoaderTest {
    private PaymentsLoader loader;

    public PaymentsLoaderTest() {
        loader = new PaymentsLoader();
    }

    @Test
    public void testReadPaymentsFromFileAllSuccess() {
        URL url = this.getClass().getClassLoader().getResource("input.txt");
        assertNotNull(url);
        List<Payment> payments = loader.readPaymentsFromFile(url.getFile());
        assertEquals(5, payments.size());
    }

    @Test
    public void testReadPaymentsFromFileNotAllSuccess() {
        URL url = this.getClass().getClassLoader().getResource("input2.txt");
        assertNotNull(url);
        List<Payment> payments = loader.readPaymentsFromFile(url.getFile());
        assertEquals(6, payments.size());
    }
 }
