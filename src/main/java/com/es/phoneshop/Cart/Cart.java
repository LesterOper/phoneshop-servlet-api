package com.es.phoneshop.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cart;
    private BigDecimal totalCartCost = new BigDecimal(0);

    public Cart() {
        cart = new ArrayList<>();
    }

    public List<CartItem> getList() {
        return cart;
    }

    public void setTotalCartCost(List<CartItem> cartItems) {
        totalCartCost = cartItems.stream()
                .map(CartItem::getTotalCost)
                .reduce(new BigDecimal("0"), (x, y) -> x.add(y));
    }

    public BigDecimal getTotalCartCost() {
        return totalCartCost;
    }
}
