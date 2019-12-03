package com.es.phoneshop.Session;

import com.es.phoneshop.Cart.Cart;
import com.es.phoneshop.Cart.CartService;
import com.es.phoneshop.Cart.HttpSessionCartService;
import com.es.phoneshop.exception.NotEnoughStockException;
import com.es.phoneshop.model.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertNotEquals;

public class HttpSessionCartServiceTest {
    private CartService cartService;
    private Cart cart;

    @Before
    public void setup() {
        cartService = HttpSessionCartService.getInstance();
        cart = new Cart();
    }

    @Test
    public void testAddProductToCart() {
        Currency usd = Currency.getInstance("USD");
        Product item1 = new Product(5L, "iphone6", "Apple iPhone 6", new BigDecimal(1000), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone%206.jpg");
        Product item = new Product(4L, "iphone", "Apple iPhone", new BigDecimal(200), usd, 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg");
        int quantity = 1;
        cartService.addProduct(this.cart, item, quantity);
        Cart cart1 = new Cart();
        cartService.addProduct(cart1, item1, 3);
        cartService.addProduct(cart, item, quantity);
        assertNotEquals(cart, cart1);
    }

    @Test(expected = NotEnoughStockException.class)
    public void testUpdateCart() {
        Currency usd = Currency.getInstance("USD");
        Product item = new Product(4L, "iphone", "Apple iPhone", new BigDecimal(200), usd, 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg");
        Cart cart1 = this.cart;
        cartService.updateCart(cart, item, 4);
        assertNotEquals(this.cart, cart1);
    }
}
