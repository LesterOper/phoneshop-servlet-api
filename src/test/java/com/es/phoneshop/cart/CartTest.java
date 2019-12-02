package com.es.phoneshop.cart;

import com.es.phoneshop.Cart.Cart;
import com.es.phoneshop.Cart.CartItem;
import com.es.phoneshop.Cart.CartService;
import com.es.phoneshop.Cart.HttpSessionCartService;
import com.es.phoneshop.model.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class CartTest {
    private Cart cart;

    @Before
    public void setup() {
        cart = new Cart();
    }

    @Test
    public void testSetTotalCartCost() {
        Currency usd = Currency.getInstance("USD");
        Product product1 = new Product(10L, "palmp", "Palm Pixi", new BigDecimal(170), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg");
        Product product2 = new Product(7L, "sec901", "Sony Ericsson C901", new BigDecimal(420), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg");
        CartItem cartItem = new CartItem(product1, 2);
        CartItem cartItem1 = new CartItem(product2, 15);
        cart.getList().add(cartItem);
        cart.getList().add(cartItem1);
        cart.setTotalCartCost(cart.getList(), new BigDecimal("5"));
        assertEquals(product1.getPrice().add(product2.getPrice().add(new BigDecimal("5"))), cart.getTotalCartCost());
    }

    @Test
    public void testSetTotalCartQuantity() {
        Currency usd = Currency.getInstance("USD");
        Product product1 = new Product(10L, "palmp", "Palm Pixi", new BigDecimal(170), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg");
        Product product2 = new Product(7L, "sec901", "Sony Ericsson C901", new BigDecimal(420), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg");
        CartItem cartItem = new CartItem(product1, 2);
        CartItem cartItem1 = new CartItem(product2, 15);
        cart.getList().add(cartItem);
        cart.getList().add(cartItem1);
        cart.setTotalCartQuantity(cart.getList());
        assertEquals(17, cart.getTotalCartQuantity());
    }
}
