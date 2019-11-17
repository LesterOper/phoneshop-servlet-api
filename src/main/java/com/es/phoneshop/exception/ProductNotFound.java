
package com.es.phoneshop.exception;


public class ProductNotFound extends RuntimeException {
    private Long id;

    public ProductNotFound(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
