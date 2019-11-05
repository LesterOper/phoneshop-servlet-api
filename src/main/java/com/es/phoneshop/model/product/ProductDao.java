package com.es.phoneshop.model.product;

import com.es.phoneshop.exception.ProductNotFound;
import java.util.List;

public interface ProductDao {
    Product getProduct(Long id) throws ProductNotFound;
    List<Product> findProducts(String query, SortField field, SortPrice price);
    void save(Product product);
    void delete(Long id);
}
