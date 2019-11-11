package com.es.phoneshop.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartList {
    private List<CartItem> cart;
    
    public CartList(){
        cart = new ArrayList<>();
    }
    
    public List<CartItem> getList(){
        return cart;
    }
}
