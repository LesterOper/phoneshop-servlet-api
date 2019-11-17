
package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;


public class CartItem {
    private Product product;
    private int quantity;
    private int totalQuantity = 0;
    private int totalCost = 0;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    public Product getProduct() {
        return product;
    }
}
