package com.es.phoneshop.order;

public interface OrderDAO {
    Order getOrder(String secureId);
    void saveOrder(Order order);
}
