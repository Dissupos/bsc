package com.bsc.paymenttracker;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ExchangeRateRepositoryTest {
    private Repository<String, ExchangeRate> repository;

    @Before
    public void beforeTest() {
        repository = new ExchangeRateRepository();
    }

    @Test
    public void testInitFromFile() {
        repository = new ExchangeRateRepository();

        assertEquals(2, repository.getAllItems().size());
    }

    @Test
    public void testGetItem() {
        assertEquals("UAH", repository.getItem("UAH").getSourceCurrency());
        assertEquals("CZK", repository.getItem("CZK").getSourceCurrency());
    }

    @Test
    public void testAddItems() {
        assertEquals(2, repository.getAllItems().size());
        repository.addItem(new ExchangeRate("RUB", "USD", new BigDecimal("0.1")));
        assertEquals(3, repository.getAllItems().size());
        repository.addItem(new ExchangeRate("RUB", "USD", new BigDecimal("0.5")));
        assertEquals(3, repository.getAllItems().size());
    }

    @Test
    public void testAddAllItems() {
        assertEquals(2, repository.getAllItems().size());
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        exchangeRates.add(new ExchangeRate("EUR", "USD", new BigDecimal("0.4")));
        exchangeRates.add(new ExchangeRate("XXX", "USD", new BigDecimal("0.5")));
        repository.addAllItems(exchangeRates);
        assertEquals(4, repository.getAllItems().size());
        exchangeRates = new ArrayList<>();
        exchangeRates.add(new ExchangeRate("EUR", "USD", new BigDecimal("0.4")));
        exchangeRates.add(new ExchangeRate("YYY", "USD", new BigDecimal("0.5")));
        repository.addAllItems(exchangeRates);
        assertEquals(5, repository.getAllItems().size());
    }

    @Test
    public void testRemoveAll() {
        assertEquals(2, repository.getAllItems().size());
        repository.removeAllItems();
        assertEquals(0, repository.getAllItems().size());
    }

    @Test
    public void testRemove() {
        assertEquals(2, repository.getAllItems().size());
        assertEquals("UAH", repository.getItem("UAH").getSourceCurrency());
        repository.removeItem(new ExchangeRate("UAH", "USD", null));
        assertEquals(1, repository.getAllItems().size());
        assertNull(repository.getItem("UAH"));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(2, repository.getAllItems().size());
        assertFalse(repository.isEmpty());
        repository.removeAllItems();
        assertEquals(0, repository.getAllItems().size());
        assertTrue(repository.isEmpty());
    }
}
