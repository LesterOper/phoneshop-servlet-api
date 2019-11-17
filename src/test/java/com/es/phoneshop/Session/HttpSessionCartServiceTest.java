package com.es.phoneshop.Session;

import com.es.phoneshop.Cart.Cart;
import com.es.phoneshop.Cart.CartService;
import com.es.phoneshop.Cart.HttpSessionCartService;
import com.es.phoneshop.exception.NotEnoughStockException;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;
import org.junit.Before;
import org.junit.Test;

import java.math.*;
import java.util.*;

import static org.junit.Assert.*;

public class HttpSessionCartServiceTest {
    private CartService cartService;
    private ProductDao product;

    @Before
    public void setup() {
        cartService = HttpSessionCartService.getInstance();
        product = ArrayListProductDao.getInstance();
    }

    @Test(expected = NotEnoughStockException.class)
    public void testAddProductToCart() {
        Currency usd = Currency.getInstance("USD");
        Cart cart = new Cart();
        Product item = new Product(4L, "iphone", "Apple iPhone", new BigDecimal(200), usd, 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg");
        int quantity = 3;
        cartService.addProduct(cart, item, quantity);
        cartService.addProduct(cart, item, quantity + 6);
        assertNotNull(cart.getList());
    }
}
