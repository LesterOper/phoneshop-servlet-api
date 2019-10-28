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
        this.product = new ArrayListProductDao();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized(this){
            try{
                Thread.sleep(5000);
            }
            catch(InterruptedException e){
                System.err.println("Interrupted");
            }
        }
        request.setAttribute("products",product.findProducts());
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }


}
