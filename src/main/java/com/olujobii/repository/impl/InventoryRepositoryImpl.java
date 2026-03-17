package com.olujobii.repository.impl;

import com.olujobii.model.Product;
import com.olujobii.repository.InventoryRepository;

import java.util.Map;

public class InventoryRepositoryImpl implements InventoryRepository {
    private final Map<String, Product> productsMap;

    public InventoryRepositoryImpl(Map<String, Product> productsMap){
        this.productsMap = productsMap;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public boolean searchByName(Product product) {
        return false;
    }
}
