package com.olujobii.model;

public record Product(String id, String name, double price, int quantity, String category) {

    public double getTotalValue(){
        return price * quantity;
    }
}
