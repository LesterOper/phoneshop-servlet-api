
package com.es.phoneshop.exception;


public class ProductNotFoundException extends RuntimeException{
    private Long id;
    
    public ProductNotFoundException(String message,Long id){
        super(message);
        this.id = id;
    }
    
    public Long getId(){
        return this.id;
    }
}
