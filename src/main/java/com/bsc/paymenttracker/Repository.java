package com.bsc.paymenttracker;

import java.util.List;

public interface Repository<K, T> {
    void addItem(T item);

    void addAllItems(List<T> items);

    void removeAllItems();

    void removeItem(T item);

    List<T> getAllItems();

    T getItem(K key);

    boolean isEmpty();
}
