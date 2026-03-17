package com.olujobii.service.impl;

import com.olujobii.model.Product;
import com.olujobii.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void updateQuantity(Product product) {

    }

    @Override
    public boolean searchByName(Product product) {
        return false;
    }

    @Override
    public List<Product> listByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> sortByPrice() {
        return List.of();
    }

    @Override
    public void removeProduct(Product product) {

    }

    @Override
    public double totalInventoryValue() {
        return 0;
    }
}
