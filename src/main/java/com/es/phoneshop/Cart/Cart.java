package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;

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
        BigDecimal summary = new BigDecimal(0);
        for (int i = 0; i < cartItems.size(); i++) {
            summary = summary.add(cartItems.get(i).getTotalCost());
        }
        totalCartCost = summary;
    }

    public BigDecimal getTotalCartCost() {
        return totalCartCost;
    }
}
