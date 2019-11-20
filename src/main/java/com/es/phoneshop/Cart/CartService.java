package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    Cart getCart(HttpServletRequest request);

    void addProduct(Cart cart, Product product, int quantity);

    RecentlyViewedItems getViewedItems(HttpServletRequest request);

    void updateCart(Cart cart, Product product, int quantity);
}
