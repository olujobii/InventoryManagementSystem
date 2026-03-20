package com.olujobii.service.impl;

import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;
import com.olujobii.repository.InventoryRepository;
import com.olujobii.service.ProductService;

import java.util.Collections;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Product> listAll(){
        return inventoryRepository.getProducts();
    }

    @Override
    public void addProduct(Product product) {
        inventoryRepository.add(product);
    }

    @Override
    public List<Product> listByCategory(ProductCategory category) {
        return inventoryRepository.findProductByCategory(category);
    }

    @Override
    public List<Product> sortByPrice() {
        List<Product> products = inventoryRepository.getProducts();

        Collections.sort(products);
        return products;
    }

    @Override
    public Product removeProduct(String productId) {
        return inventoryRepository.remove(productId);
    }

    @Override
    public double totalInventoryValue() {
        double total = 0 ;
        List<Product> products = inventoryRepository.getProducts();

        for(Product product : products){
            total += product.getTotalValue();
        }

        return total;
    }

    @Override
    public Product getProductById(String id){
        return inventoryRepository.findProductById(id);
    }

    @Override
    public List<Product> searchProductName(String productName){
        return inventoryRepository.findProductByName(productName);
    }

    @Override
    public boolean isProductAvailable(){
        return inventoryRepository.getProducts().isEmpty();
    }
}
