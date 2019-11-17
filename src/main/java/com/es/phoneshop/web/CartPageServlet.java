package com.es.phoneshop.web;

import com.es.phoneshop.Cart.*;
import com.es.phoneshop.exception.NotEnoughStockException;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartPageServlet extends HttpServlet {
    private static final String QUANTITY = "quantity";
    private CartService cartService = HttpSessionCartService.getInstance();
    private Cart cartList = new Cart();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cartList = cartService.getCart(request);
        showPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] quantity = request.getParameterValues("quantity");
        String[] productId = request.getParameterValues("productId");
        String error = "";
        ProductDao productDao = ArrayListProductDao.getInstance();
        Product product = null;
        cartList = cartService.getCart(request);
        Map<Product, String> errors = new HashMap<>();
        for (int i = 0; i < productId.length; i++) {
            try {
                Long id = Long.parseLong(productId[i]);
                product = productDao.getProduct(id);
                int kolvo = Integer.parseInt(quantity[i]);
                cartService.updateCart(cartList, product, kolvo);
            } catch (NumberFormatException ex) {
                errors.put(product, "Not a number");
            } catch (NotEnoughStockException e) {
                errors.put(product, "Not enough stock");
            }
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            showPage(request, response);
            return;
        } else {
            response.sendRedirect(request.getRequestURI() + "?message=Updated successfully");
        }
    }

    public void showPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("products",
        //      product.getProduct(Long.parseLong(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1))));
        request.getRequestDispatcher("/WEB-INF/pages/cartPage.jsp").
                forward(request, response);
    }

    private int getLocale(HttpServletRequest request, String quantity) throws ParseException {
        Locale locale = request.getLocale();
        return NumberFormat.getIntegerInstance(locale).parse(quantity).intValue();
    }
}
