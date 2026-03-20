package com.olujobii.model;

import com.olujobii.enums.ProductCategory;

public record Product(String id, String name, double price, int quantity, ProductCategory category) implements Comparable<Product> {

    public double getTotalValue(){
        return price * quantity;
    }

    @Override
    public int compareTo(Product other){
        return Double.compare(this.price, other.price);
    }
}
