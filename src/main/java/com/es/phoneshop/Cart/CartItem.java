
package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;


public class CartItem {
    private Product product;
    private int quontity;
    private int totalQuontity = 0;
    private int totalCost = 0;
    
    public CartItem (Product product, int quontity){
        this.product = product;
        this.quontity = quontity;
    }
    
    public int getQountity(){
        return this.quontity;
    }
    
    public void setQuontity(int quontity){
        this.quontity += quontity;
    }
    
    public Product getProduct(){
        return product;
    }
}
