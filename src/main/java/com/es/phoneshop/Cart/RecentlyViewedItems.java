package com.es.phoneshop.Cart;

import com.es.phoneshop.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class RecentlyViewedItems {
    private List<Product> recentlyViewed;

    public RecentlyViewedItems(){
        recentlyViewed = new ArrayList<>();
    }

    public boolean isContainsProduct(Product product){
        return recentlyViewed.contains(product);
    }

    public void addToRecentlyViewed(Product product){
        recentlyViewed.add(product);
    }

    public int getLength(){
        return recentlyViewed.size();
    }

    public List<Product> getList(){
        return recentlyViewed;
    }
}
