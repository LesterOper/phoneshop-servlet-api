package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;
import javax.servlet.http.HttpServletRequest;

public class HttpSessionCartService implements Cart{

    private static Cart instance;
    
    private HttpSessionCartService(){
    }
    
    public static Cart getInstance(){
        if(instance == null){
            synchronized(HttpSessionCartService.class){
                if(instance==null){
                    instance = new HttpSessionCartService();
                }
            }
        }
        return  instance;
    }
    
    @Override
    public CartList getCart(HttpServletRequest request) {
        CartList cart = null;
        if(request.getSession().getAttribute("cartList")==null){
            cart = new CartList();
            request.getSession().setAttribute("cartList", cart);
        }
        else{
            cart = (CartList)request.getSession().getAttribute("cartList");
        }
        return cart;
    }

    @Override
    public void addProduct(CartList cartList,Product product, int quontity) {
        if(cartList.getList().stream().anyMatch(prod-> prod.getProduct().getId().equals(product.getId()))){
            cartList.getList().stream().
                    filter(prod-> prod.getProduct().getId().
                    equals(product.getId())).
                    map(prod-> prod.getQountity()+quontity);
        }
        else{
            cartList.getList().add(new CartItem(product, quontity));
        }
    } 
}
