package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;
import javax.servlet.http.HttpServletRequest;

public interface Cart {
    CartList getCart(HttpServletRequest request);
    void addProduct(CartList cart, Product product, int quontity); 
}
