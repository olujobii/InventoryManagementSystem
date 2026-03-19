package com.olujobii.repository;

import com.olujobii.model.Product;

import java.util.Map;

public interface InventoryRepository {

    void add(Product product);

    Product remove(String productId);

    boolean searchByName(Product product);

    Map<String, Product> getProducts();

    Product getProductFromInventory(String id);
}
