package com.es.phoneshop.model.product;

import com.es.phoneshop.exception.ProductNotFoundException;
import java.util.List;

public interface ProductDao {
    Product getProduct(Long id) throws ProductNotFoundException;
    List<Product> findProducts(String query, SortField field, SortPrice price);
    void save(Product product);
    void delete(Long id);
}
