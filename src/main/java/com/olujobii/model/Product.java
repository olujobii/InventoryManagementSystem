package com.olujobii.model;

import com.olujobii.enums.ProductCategory;

public record Product(String id, String name, double price, int quantity, ProductCategory category) {

    public double getTotalValue(){
        return price * quantity;
    }
}
