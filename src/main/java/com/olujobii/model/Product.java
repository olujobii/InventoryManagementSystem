package com.olujobii.model;

import com.olujobii.enums.ProductCategory;

public record Product(String id, String name, double price, int quantity, ProductCategory category) {
    private static int idGenerator = 1;

    public Product(String id, String name, double price, int quantity, ProductCategory category){
       this.id = id + idGenerator;
       this.name = name;
       this.price = price;
       this.quantity = quantity;
       this.category = category;

       idGenerator++;
    }
    public double getTotalValue(){
        return price * quantity;
    }
}
