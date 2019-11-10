
package com.es.phoneshop.web;

import com.es.phoneshop.Cart.*;
//import com.es.phoneshop.Cart.HttpSessionCartService;
import static com.es.phoneshop.web.ProductListPageServlet.product;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ProductDetailsServlet extends HttpServlet{
    
    private Cart cartService = HttpSessionCartService.getInstance();
    private CartList cartList = new CartList();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //showPage(request, response, cartList);
        request.setAttribute("products",
                    product.getProduct(Long.parseLong(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1))));
        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("/WEB-INF/pages/productDescription.jsp").
                forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Long id = Long.parseLong(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1));
        int quontity = Integer.parseInt(request.getParameter("quontity"));
        cartList = cartService.getCart(request);
        cartList = cartService.addProduct(cartList, product.getProduct(id), quontity);
        showPage(request, response, cartList);
    }
    
    public void showPage(HttpServletRequest request, HttpServletResponse response, CartList cart) throws ServletException, IOException{
        request.setAttribute("products",
                    product.getProduct(Long.parseLong(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1))));
        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("/WEB-INF/pages/productDescription.jsp").
                forward(request, response);
    }
}
