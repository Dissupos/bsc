package com.bsc.paymenttracker;

public interface Parser<T> {
    T parse(String input);
}
