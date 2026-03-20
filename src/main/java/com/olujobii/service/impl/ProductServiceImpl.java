package com.olujobii.service.impl;

import com.olujobii.enums.ProductCategory;
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
    public List<Product> listByCategory(ProductCategory category) {
        Map<String,Product> maps = inventoryRepository.getProducts();

        List<Product> lists = new ArrayList<>();
        for(Product product : maps.values()){
            if(product.category() == category)
                lists.add(product);
        }

        return lists;
    }

    @Override
    public List<Product> sortByPrice() {
        return List.of();
    }

    @Override
    public Map<String, Product> getAllProducts(){
        return inventoryRepository.getProducts();
    }

    @Override
    public Product removeProduct(String productId) {
        return inventoryRepository.remove(productId);
    }

    @Override
    public double totalInventoryValue() {
        double total = 0 ;
        Map<String, Product> products = inventoryRepository.getProducts();

        for(Product product : products.values()){
            total += product.getTotalValue();
        }

        return total;
    }

    @Override
    public boolean isKeyAvailable(String userInputId){
        Map<String,Product> products = inventoryRepository.getProducts();

        return products.containsKey(userInputId);
    }

    @Override
    public Product createNewProduct(String id, String productName, double price, int quantity, ProductCategory productCategory){
        return new Product(id,productName,price,quantity,productCategory);
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
