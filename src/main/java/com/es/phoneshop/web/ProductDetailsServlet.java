
package com.es.phoneshop.web;

import static com.es.phoneshop.web.ProductListPageServlet.product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProductDetailsServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("products",product.getProduct(Long.parseLong(request.getParameter("prod"))));
        request.getRequestDispatcher("/WEB-INF/pages/productDescription.jsp").forward(request, response);
    }
}
