package com.olujobii.service.impl;

import com.olujobii.model.Product;
import com.olujobii.repository.InventoryRepository;
import com.olujobii.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private final InventoryRepository inventoryRepository;

    public ProductServiceImpl(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void addProduct(Product product) {
        inventoryRepository.add(product);
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
    public Product removeProduct(String productId) {
        return inventoryRepository.remove(productId);
    }

    @Override
    public double totalInventoryValue() {
        return 0;
    }

    @Override
    public boolean isKeyAvailable(String userInputId){
        Map<String,Product> products = inventoryRepository.getProducts();

        return products.containsKey(userInputId);
    }

    @Override
    public Product getProduct(String id){
        return inventoryRepository.getProductFromInventory(id);
    }

    @Override
    public Product updateProductQuantity(Product product, int quantity){
        return new Product(product.id(), product.name(), product.price(), quantity,product.category());
    }

    @Override
    public List<Product> searchProductName(String productName){
        Map<String, Product> products = inventoryRepository.getProducts();
        List<Product> productList = new ArrayList<>();

        for(Product product : products.values()){
            if(product.name().equalsIgnoreCase(productName))
                productList.add(product);
        }

        return productList;
    }
}
