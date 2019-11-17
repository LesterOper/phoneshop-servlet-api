package com.es.phoneshop.web;

import com.es.phoneshop.model.product.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {
    private static final String SORT = "sort";
    public static ProductDao product;


    public ProductListPageServlet() {
        this.product = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SortPrice price = SortPrice.no;
        SortField field = SortField.no;
        if (request.getParameter(SORT) != null) {
            String board = request.getParameter("Board");
            if (request.getParameter(SORT).equals("description")) {
                field = SortField.valueOf(board);
            }
            if (request.getParameter(SORT).equals("price")) {
                price = SortPrice.valueOf(board);
            }
        }
        String search = request.getParameter("query");

        request.setAttribute("products", product.findProducts(search, field, price));
        if (product.findProducts(search, field, price).isEmpty()) {
            request.getRequestDispatcher("/WEB-INF/pages/productCan'tBeFound.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
        }
    }
}



