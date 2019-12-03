package com.es.phoneshop.order;

import com.es.phoneshop.Cart.Cart;
import com.es.phoneshop.Cart.CartService;
import com.es.phoneshop.Cart.HttpSessionCartService;
import com.es.phoneshop.model.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ArrayListOrderDaoTest {
    private OrderService orderService;
    private CartService cartService;
    private Cart cart;

    @Before
    public void setup() {
        orderService = DefaultOrderService.getInstance();
        cartService = HttpSessionCartService.getInstance();
    }

    @Test
    public void testGetOrder() {
        cart = new Cart();
        Currency usd = Currency.getInstance("USD");
        Product product = new Product(7L, "sec901", "Sony Ericsson C901", new BigDecimal(420), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg");
        Product product1 = new Product(4L, "iphone", "Apple iPhone", new BigDecimal(200), usd, 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg");
        cartService.addProduct(cart, product, 3);
        cartService.addProduct(cart, product1, 4);
        Order order = orderService.getOrder(cart);
        String id = orderService.placeOrder(order);
        Order order1 = ArrayListOrderDao.getInstance().getOrder(id);
        assertNotNull(order1);
    }
}
