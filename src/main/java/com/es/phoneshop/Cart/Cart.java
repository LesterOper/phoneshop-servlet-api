package com.es.phoneshop.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cart;
    private BigDecimal totalCartCost;
    private int totalCartQuantity;

    public Cart() {
        cart = new ArrayList<>();
    }

    protected Cart(List<CartItem> cartItems) {
        this.cart = cartItems;
    }

    public List<CartItem> getList() {
        return cart;
    }

    public void setTotalCartCost(List<CartItem> cartItems, BigDecimal delivery) {
        totalCartCost = cartItems.stream()
                .map(CartItem::getTotalCost)
                .reduce(new BigDecimal("0"), BigDecimal::add);
        this.totalCartCost = totalCartCost.add(delivery);

    }

    public BigDecimal getTotalCartCost() {
        return totalCartCost;
    }

    public void setTotalCartQuantity(List<CartItem> cartItems) {
        totalCartQuantity = cartItems.stream()
                .map(CartItem::getQuantity)
                .reduce(totalCartQuantity, Integer::sum);
    }

    public int getTotalCartQuantity() {
        return this.totalCartQuantity;
    }
}
