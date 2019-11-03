package com.es.phoneshop.web;

import com.es.phoneshop.model.product.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {
    
    public static ProductDao product;
    

    public ProductListPageServlet() {
        this.product = ArrayListProductDao.getInstance();
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SortPrice price = SortPrice.no;
        SortField field = SortField.no;
        if(request.getParameter("sort")!=null){
            String board = request.getParameter("Board");
            if(request.getParameter("sort").equals("description")){
                field = SortField.valueOf(board);
            }
            if(request.getParameter("sort").equals("price")){
                price = SortPrice.valueOf(board);
            }
        }
        
        request.setAttribute("products",product.findProducts(request.getParameter("query"), field, price));
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}



