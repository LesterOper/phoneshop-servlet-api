package com.es.phoneshop.web;

import com.es.phoneshop.Cart.*;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteItemFromCartServlet extends HttpServlet {
    private CartService cartService = HttpSessionCartService.getInstance();
    private Cart cart;
    private ProductDao productDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long productId = Long.parseLong(request.getParameter("productId"));
        cart = cartService.getCart(request);
        productDao = ArrayListProductDao.getInstance();
        Product product = productDao.getProduct(productId);
        Iterator<CartItem> cartItem = cart.getList().iterator();
        while (cartItem.hasNext()) {
            CartItem item = cartItem.next();
            if (item.getProduct().getId().equals(product.getId())) {
                cartItem.remove();
                cart.setTotalCartCost(cart.getList());
            }
        }
        response.sendRedirect(request.getContextPath() + "/products/cart");
    }
}
