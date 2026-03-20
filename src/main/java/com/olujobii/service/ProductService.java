package com.olujobii.service;

import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    void addProduct(Product product);

    List<Product> listByCategory(ProductCategory category);

    List<Product> sortByPrice();

    Product removeProduct(String productId);

    Map<String, Product> getAllProducts();

    double totalInventoryValue();

    boolean isKeyAvailable(String userInputId);

    Product createNewProduct(String id, String productName, double price, int quantity, ProductCategory productCategory);

    Product getProduct(String id);

    Product updateProductQuantity(Product product, int quantity);

    List<Product> searchProductName(String productName);
}
