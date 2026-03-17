package com.olujobii.service;

import com.olujobii.model.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    void updateQuantity(Product product);

    boolean searchByName(Product product);

    List<Product> listByCategory(String category);

    List<Product> sortByPrice();

    void removeProduct(Product product);

    double totalInventoryValue();
}
