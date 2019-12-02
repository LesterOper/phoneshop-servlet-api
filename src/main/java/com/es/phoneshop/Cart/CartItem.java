
package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;

import java.math.BigDecimal;


public class CartItem {
    private Product product;
    private int quantity;
    private BigDecimal totalCost;

    public CartItem(Product product, int quantity) {
        this.product = product;
        setQuantity(quantity, product.getPrice());
        this.totalCost = product.getPrice();
    }

    public CartItem(CartItem cartItem){
        this.product = cartItem.product;
        this.quantity = cartItem.getQuantity();
        this.totalCost = cartItem.getTotalCost();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity, BigDecimal price) {
        this.quantity = quantity;
        setTotalCost(price, quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setTotalCost(BigDecimal cost, int quantity) {
        BigDecimal quantityMultiple = new BigDecimal(quantity);
        this.totalCost = cost.multiply(quantityMultiple);
    }

    public BigDecimal getTotalCost() {
        return this.totalCost;
    }
}
