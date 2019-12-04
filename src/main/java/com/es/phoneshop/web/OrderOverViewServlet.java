package com.es.phoneshop.web;

import com.es.phoneshop.order.DefaultOrderService;
import com.es.phoneshop.order.Order;
import com.es.phoneshop.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderOverViewServlet extends HttpServlet {

    private OrderService orderService = DefaultOrderService.getInstance();
    private final static String ORDER = "order";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = orderService.getOrder(getOrderId(request));

        request.setAttribute(ORDER, order);
        request.getRequestDispatcher("/WEB-INF/pages/orderview.jsp").
                forward(request, response);
    }

    private String getOrderId(HttpServletRequest request) {
        return request.getRequestURI()
                .substring(request.getRequestURI()
                        .lastIndexOf("/")+1);
    }
}
