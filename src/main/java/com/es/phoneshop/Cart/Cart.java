
package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface Cart {
    CartList getCart(HttpServletRequest request);
    CartList addProduct(CartList cart, Product product, int quontity);
    
}
