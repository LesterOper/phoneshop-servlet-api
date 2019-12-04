package com.es.phoneshop.order;

import com.es.phoneshop.Cart.Cart;

public interface OrderService {
    Order getOrder(Cart cart);
    String placeOrder(Order order);
    Order getOrder(String secureId);
}
