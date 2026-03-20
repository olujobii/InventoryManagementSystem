package com.olujobii.service;

import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAll();

    void addProduct(Product product);

    List<Product> listByCategory(ProductCategory category);

    List<Product> sortByPrice();

    Product removeProduct(String productId);

    double totalInventoryValue();

    boolean isProductAvailable();

    Product getProductById(String id);

    List<Product> searchProductName(String productName);
}
