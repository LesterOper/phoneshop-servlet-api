package com.es.phoneshop.model.product;

import com.es.phoneshop.exception.ProductNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.math.*;
import java.util.*;


import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    private ProductDao productDao;

    @Before
    public void setup() {
        productDao = ArrayListProductDao.getInstance();
    }

    @Test
    public void testFindProductsNoResults() {
        assertFalse(productDao.findProducts("", SortField.asc, SortPrice.desc).isEmpty());
    }

    @Test(expected = ProductNotFoundException.class)
    public void testGetProduct() {
        assertNotNull(productDao.getProduct(1L));
    }

    @Test(expected = IllegalArgumentException.class)
    public synchronized void testSaveProduct() {
        Product productTest = new Product(14L, "nokia111", "Nokia 355", new BigDecimal(50), Currency.getInstance("USD"), 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        productDao.save(productTest);
        productDao.save(productTest);
        assertNotNull(productDao.getProduct(productTest.getId()));
    }

    @Test
    public synchronized void testDeleteProduct() {
        ProductDao ExpectedProduct = ArrayListProductDao.getInstance();
        productDao.delete(1L);
        assertEquals("Equals", ExpectedProduct, productDao);
    }
}
