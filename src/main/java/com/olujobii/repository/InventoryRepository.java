package com.olujobii.repository;

import com.olujobii.model.Product;

import java.util.List;
import java.util.Map;

public interface InventoryRepository {

    void add(Product product);

    Product remove(String productId);

    Map<String, Product> getProducts();

    Product getProductFromInventory(String id);
}
