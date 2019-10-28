package com.es.phoneshop.model.product;

import org.junit.Before;
import org.junit.Test;
import java.math.*;
import java.util.*;


import static org.junit.Assert.*;

public class ArrayListProductDaoTest
{
    private ProductDao productDao;
    
    @Before
    public void setup() {
        productDao = new ArrayListProductDao();
    }

    @Test
    public void testFindProductsNoResults() {
        assertFalse(productDao.findProducts().isEmpty());
    }
    
    @Test
    public void testGetProduct(){
        assertNotNull(productDao.getProduct(4L));
    }
    
    @Test
    public void testSaveProduct(){
        Product productTest = new Product(14L, "nokia111", "Nokia 355", new BigDecimal(50), Currency.getInstance("USD"), 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg" );
        productDao.save(productTest);
        productDao.save(productTest);
        assertNotNull(productDao.getProduct(productTest.getId()));
    }
    
    @Test
    public void testDeleteProduct(){
        ProductDao ExpectedProduct = new ArrayListProductDao();
        productDao.delete(10L);
        assertNotEquals("Equals",ExpectedProduct, productDao);
    }
}
