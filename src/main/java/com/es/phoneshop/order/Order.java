package com.es.phoneshop.order;

import com.es.phoneshop.Cart.Cart;
import com.es.phoneshop.Cart.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class Order extends Cart {

    private BigDecimal subtotal;
    private BigDecimal deliveryCost;

    private String secureId;
    private String name;
    private String phone;
    private String date;
    private String address;

    public Order(List<CartItem> cartItems){
        super(cartItems);
    }

    public void setDeliveryCost(BigDecimal delivery) {
        this.deliveryCost = delivery;
    }

    public BigDecimal getDeliveryCost() {
        return this.deliveryCost;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getSubtotal() {
        return this.subtotal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }

    public String getSecureId() {
        return this.secureId;
    }
}
