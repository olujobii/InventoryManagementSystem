package com.olujobii.service;

import com.olujobii.model.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    boolean searchByName(Product product);

    List<Product> listByCategory(String category);

    List<Product> sortByPrice();

    Product removeProduct(String productId);

    double totalInventoryValue();

    boolean isKeyAvailable(String userInputId);

    Product getProduct(String id);

    Product updateProductQuantity(Product product, int quantity);

    List<Product> searchProductName(String productName);
}
