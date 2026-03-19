package com.olujobii.repository.impl;

import com.olujobii.model.Product;
import com.olujobii.repository.InventoryRepository;

import java.util.Map;

public class InventoryRepositoryImpl implements InventoryRepository {
    private final Map<String, Product> products;

    public InventoryRepositoryImpl(Map<String, Product> products){
        this.products = products;
    }

    @Override
    public void add(Product product) {
        products.put(product.id(),product);
    }

    @Override
    public Product remove(String productId) {
        return products.remove(productId);
    }

    @Override
    public boolean searchByName(Product product) {
        return false;
    }

    @Override
    public Map<String,Product> getProducts(){
        return products;
    }

    @Override
    public Product getProductFromInventory(String id){
        return products.get(id);
    }
}
