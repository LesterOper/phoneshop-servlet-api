package com.es.phoneshop.Cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cart;
    
    public Cart(){
        cart = new ArrayList<>();
    }
    
    public List<CartItem> getList(){
        return cart;
    }
}
