package com.bsc.paymenttracker;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PaymentRepositoryTest {
    private Repository<String, Payment> repository;

    @Before
    public void beforeTest() {
        repository = new PaymentRepository(null);
    }

    @Test
    public void testGetItem() {
        Payment testPayment = new Payment("UAH", new BigDecimal("1"));
        repository.addItem(testPayment);
        Payment payment = repository.getItem("UAH");
        assertEquals(testPayment.getCurrency(), payment.getCurrency());
        assertEquals(testPayment.getAmount(), payment.getAmount());
    }

    @Test
    public void testAddItems() {
        assertEquals(0, repository.getAllItems().size());
        repository.addItem(new Payment("RUB", new BigDecimal("0.1")));
        assertEquals(1, repository.getAllItems().size());
        repository.addItem(new Payment("RUB", new BigDecimal("0.5")));
        assertEquals(1, repository.getAllItems().size());
    }

    @Test
    public void testAddAllItems() {
        assertEquals(0, repository.getAllItems().size());
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment("EUR", new BigDecimal("0.4")));
        payments.add(new Payment("XXX", new BigDecimal("0.5")));
        repository.addAllItems(payments);
        assertEquals(2, repository.getAllItems().size());
        payments = new ArrayList<>();
        payments.add(new Payment("EUR", new BigDecimal("0.4")));
        payments.add(new Payment("YYY", new BigDecimal("0.5")));
        repository.addAllItems(payments);
        assertEquals(3, repository.getAllItems().size());
    }

    @Test
    public void testRemoveAll() {
        repository.addItem(new Payment("UAH", new BigDecimal("98")));
        assertEquals(1, repository.getAllItems().size());
        repository.removeAllItems();
        assertEquals(0, repository.getAllItems().size());
    }

    @Test
    public void testRemove() {

        repository.addItem(new Payment("UAH", new BigDecimal("498")));
        repository.addItem(new Payment("CZK", new BigDecimal("413")));
        assertEquals(2, repository.getAllItems().size());
        assertEquals("UAH", repository.getItem("UAH").getCurrency());
        repository.removeItem(new Payment("UAH", null));
        assertEquals(1, repository.getAllItems().size());
        assertNull(repository.getItem("UAH"));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(0, repository.getAllItems().size());
        assertTrue(repository.isEmpty());
        repository.addItem(new Payment("BLA", new BigDecimal("456")));
        assertEquals(1, repository.getAllItems().size());
        assertFalse(repository.isEmpty());
    }
}
