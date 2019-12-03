package com.es.phoneshop.web;

import com.es.phoneshop.Cart.Cart;
import com.es.phoneshop.Cart.CartService;
import com.es.phoneshop.Cart.HttpSessionCartService;
import com.es.phoneshop.order.DefaultOrderService;
import com.es.phoneshop.order.Order;
import com.es.phoneshop.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CheckoutPageServlet extends HttpServlet {
    private CartService cartService = HttpSessionCartService.getInstance();
    private OrderService orderService = DefaultOrderService.getInstance();

    private final String ORDER = "order";
    private final String ERRORMAP = "error";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = cartService.getCart(request);
        Order order = orderService.getOrder(cart);

        request.setAttribute(ORDER, order);
        showPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = cartService.getCart(request);
        Order order = orderService.getOrder(cart);
        Map<String, String> errorMap = new HashMap<>();
        String name = checkFields(request, "name",errorMap);
        String phone = checkFields(request, "phone",errorMap);
        String date = checkFields(request,"date" ,errorMap);
        String address = checkFields(request, "address",errorMap);
        String delivery = request.getParameter("deliver");

        if(!errorMap.isEmpty()) {
            request.setAttribute(ORDER, order);
            request.setAttribute(ERRORMAP, errorMap);
            showPage(request, response);
            return;
        }

        order.setName(name);
        order.setPhone(phone);
        order.setDate(date);
        order.setAddress(address);
        order.setHowToDeliver(delivery);
        String secureId = orderService.placeOrder(order);
        cart.getList().clear();
        cart.setTotalCartCost(cart.getList(), new BigDecimal("0"));
        response.sendRedirect(request.getContextPath() + "/overview/" + secureId);
    }

    private String checkFields(HttpServletRequest request, String field ,Map<String,String> errorMap){
        String nameField = request.getParameter(field);
        if(nameField == null || nameField.isEmpty()){
            errorMap.put(field, "Field is required");
        }
        return nameField;
    }

    public void showPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/checkoutPage.jsp").
                forward(request, response);
    }
}
