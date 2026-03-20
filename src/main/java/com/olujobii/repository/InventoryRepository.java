package com.olujobii.repository;

import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;

import java.util.List;

public interface InventoryRepository {

    void add(Product product);

    Product remove(String productId);

    List<Product> getProducts();

    Product findProductById(String id);

    List<Product> findProductByName(String productName);

    List<Product> findProductByCategory(ProductCategory productCategory);
}
