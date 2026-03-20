package com.olujobii.service.impl;

import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;
import com.olujobii.repository.InventoryRepository;
import com.olujobii.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        List<Product> products = inventoryRepository.getProducts();
        List<Product> searchedProducts = new ArrayList<>();

        for(Product product : products){
            if(product.category() == category)
                searchedProducts.add(product);
        }

        return searchedProducts;
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
        List<Product> products = inventoryRepository.getProducts();
        List<Product> searchedProducts = new ArrayList<>();

        for(Product product : products){
            if(product.name().equalsIgnoreCase(productName))
                searchedProducts.add(product);
        }

        return searchedProducts;
    }

    @Override
    public boolean isProductAvailable(){
        return inventoryRepository.getProducts().isEmpty();
    }
}
