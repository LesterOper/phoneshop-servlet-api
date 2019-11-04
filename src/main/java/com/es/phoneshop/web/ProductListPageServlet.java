package com.es.phoneshop.web;

import com.es.phoneshop.model.product.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {
    
    public static ProductDao product;
    private SortPrice price;
    private SortField field;
    
    public ProductListPageServlet() {
        this.product = ArrayListProductDao.getInstance();
        this.field = SortField.no;
        this.price = SortPrice.no;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("sort")!=null){
            String board = request.getParameter("Board");
            if(request.getParameter("sort").equals("description")){
                field = SortField.valueOf(board);
            }
            if(request.getParameter("sort").equals("price")){
                price = SortPrice.valueOf(board);
            }
        }
        String search = request.getParameter("query");
        request.setAttribute("products",product.findProducts(search, field, price));
        if(product.findProducts(search, field, price).isEmpty()){
            request.getRequestDispatcher("/WEB-INF/pages/productCan'tBeFound.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
        }
    }
}



