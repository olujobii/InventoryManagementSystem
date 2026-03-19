package com.olujobii.repository;

import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;

import java.util.Map;
import java.util.List;

public interface InventoryRepository {

    void add(Product product);

    void update(Product product);

    boolean searchByName(Product product);

    Map<String, Product> getProducts();

    Product getProductFromInventory(String id);
}
