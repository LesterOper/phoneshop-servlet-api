
package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;


public class HttpSessionCartService implements Cart{

    private static Cart instance;
    private CartList cart;
    //private Cart cart = (Cart)cartItem;
    
    private HttpSessionCartService(){
        cart = new CartList();
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
    public CartList addProduct(CartList cartList,Product product, int quontity) {
        if(cartList.getList().stream().anyMatch(prod-> prod.getProduct().getId().equals(product.getId()))){
            cartList.getList().stream().
                    filter(prod-> prod.getProduct().getId().
                    equals(product.getId())).
                    map(prod-> prod.getQountity()+quontity);
        }
        else{
            cartList.getList().add(new CartItem(product, quontity));
        }
        return cartList;
    }
    
    
}
