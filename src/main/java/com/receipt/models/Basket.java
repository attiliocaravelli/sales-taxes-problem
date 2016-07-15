package com.receipt.models;

import java.util.List;

/**
 * General CRUD interface
 * @author attilio
 *
 * @param <T>
 */
public interface Basket<T> {
     void    addItem(T t);
     boolean containItem(T t);
     boolean removeItem(T t);
     List<T> getAllItems();
     void    clearItems();
     int     numItems();
}
