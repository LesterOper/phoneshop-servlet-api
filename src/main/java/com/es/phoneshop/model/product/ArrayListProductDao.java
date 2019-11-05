package com.es.phoneshop.model.product;

import com.es.phoneshop.exception.ProductNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
   
    private List<Product> result;
    private static ProductDao instance = new ArrayListProductDao();
    
    private ArrayListProductDao(){
        this.result = new ArrayList<>();
    }
    
    public static ProductDao getInstance(){
        if(instance == null){
            instance = new ArrayListProductDao();
        }
        return  instance;
    }
    
    public Comparator<Product> getComparator(SortField field, SortPrice price){
        if(field.equals(SortField.no) && price.equals(SortPrice.no)){
            return Comparator.comparing(Product::getStock);
        }
        if(field.equals(SortField.asc)){
            return Comparator.comparing(Product::getDescription);
        }
        if(field.equals(SortField.desc)){
            return Comparator.comparing(Product::getDescription).reversed();
        }
        
        if(price.equals(SortPrice.asc)){
            return Comparator.comparing(Product::getPrice);
        }
        else{
            return Comparator.comparing(Product::getPrice).reversed();
        }
    }
    
    @Override
    public Product getProduct(Long id) throws ProductNotFoundException {
         return result.stream().
                filter(x-> x.getId()!=null && x.getId().equals(id)).
                findFirst().
                orElseThrow(()->new ProductNotFoundException("Such product doesn't exist", id));
    }
    
    @Override
    public List<Product> findProducts(String query, SortField field, SortPrice price) {
        if(query!=null){
            String[] search = query.toLowerCase().split(" ");
            ToIntFunction<Product> matches = product->(int)Arrays.stream(search).
                    filter(product.getDescription().toLowerCase()::contains).count();
            
            return result.stream().
                filter(x-> x.getPrice()!=null && x.getStock()>0).
                filter(x-> Arrays.stream(search).
                        anyMatch(x.getDescription().toLowerCase()::contains)).
                sorted(Comparator.comparingInt(matches).reversed()).
                collect(Collectors.toCollection(ArrayList::new));
        }
        else{
            return result.stream()
                .filter(x-> x.getPrice()!=null && x.getStock()>0).
                sorted(getComparator(field, price)).
                collect(Collectors.toCollection(ArrayList::new));
        }
    }

    @Override
    public synchronized void save(Product product) {
        if(this.result.stream().anyMatch(x-> x.getId().equals(product.getId()))){
            throw new IllegalArgumentException();
        }
        else{
            this.result.add(product);
        }
    }

    @Override
    public synchronized void delete(Long id) {
        result.stream().filter(x-> x.getId()!=null||x.getId().equals(id)).
                findFirst().
                map(x->result.remove(id.intValue()-1)).
                orElseThrow(NullPointerException::new);
    }
}
