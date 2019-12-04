package com.es.phoneshop.comments;

public class Comment {
    private String name;
    private int rate;
    private String comment;
    private Long product_id;

    public Comment() {
        name = null;
        rate = 0;
        comment = null;
        product_id = null;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
}
