package com.es.phoneshop.web;

import com.es.phoneshop.Cart.*;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ProductDao;
import static com.es.phoneshop.web.ProductListPageServlet.product;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.objects.Global;



public class ProductDetailsServlet extends HttpServlet{
    
    private Cart cartService = HttpSessionCartService.getInstance();
    private CartList cartList = new CartList();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        showPage(request, response); 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String error = "";
        String message = "";
        try{
            Long id = Long.parseLong(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1));
            int quontity = Integer.parseInt(request.getParameter("quontity"));
            ProductDao product = ArrayListProductDao.getInstance();
            if(product.getProduct(id).getStock()<quontity){
                throw new IllegalArgumentException();
            }
            
            cartList = cartService.getCart(request);
            cartService.addProduct(cartList, product.getProduct(id), quontity);
            cartList =(CartList) request.getSession().getAttribute("cartList");
            message = "Added succesful";
        }
        catch(NumberFormatException e){
            error = "Quontity is not a number";
        }
        catch(IllegalArgumentException e){
            error = "Not enough stock";
        }
        
        if(!error.isEmpty()){
            request.setAttribute("error", error);
            showPage(request, response);
            return;
        }
        else{
            response.sendRedirect(request.getRequestURI() + "?message="+message);
        }
    }
    
    public void showPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("products",
                    product.getProduct(Long.parseLong(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1))));
        request.getRequestDispatcher("/WEB-INF/pages/productDescription.jsp").
                forward(request, response);
    }
}
