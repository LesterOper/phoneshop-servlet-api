package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionCartServiceService implements CartService {

    private static final String CART_ATTRIBUTE = "cartList";
    private static final String VIEWED_ITEMS = "viewed";
    private static CartService instance;
    
    private HttpSessionCartServiceService(){
    }
    
    public static CartService getInstance(){
        if(instance == null){
            synchronized(HttpSessionCartServiceService.class){
                if(instance==null){
                    instance = new HttpSessionCartServiceService();
                }
            }
        }
        return  instance;
    }
    
    @Override
    public Cart getCart(HttpServletRequest request) {
        Cart cart;
        HttpSession session = request.getSession();
        if(session.getAttribute(CART_ATTRIBUTE)==null){
            cart = new Cart();
            session.setAttribute(CART_ATTRIBUTE, cart);
        }
        else{
            cart = (Cart)session.getAttribute(CART_ATTRIBUTE);
        }
        return cart;
    }

    @Override
    public void addProduct(Cart cartList,Product product, int quantity) {
        if(cartList.getList().stream().anyMatch(prod-> prod.getProduct().getId().equals(product.getId()))){
            cartList.getList().stream().
                    filter(prod-> prod.getProduct().getId().equals(product.getId())).
                    map(prod-> prod.getQuantity()+quantity);
        }
        else{
            cartList.getList().add(new CartItem(product, quantity));
        }
    }

    @Override
    public RecentlyViewedItems getViewedItems(HttpServletRequest request) {
        RecentlyViewedItems items;
        HttpSession session = request.getSession();
        if(session.getAttribute(VIEWED_ITEMS)==null){
            items = new RecentlyViewedItems();
            session.setAttribute(VIEWED_ITEMS, items);
            return items;
        }
        else{
            items = (RecentlyViewedItems)session.getAttribute(VIEWED_ITEMS);
            return items;
        }
    }
}
