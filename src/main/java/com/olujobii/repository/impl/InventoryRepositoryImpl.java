package com.olujobii.repository.impl;

import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;
import com.olujobii.repository.InventoryRepository;

import java.util.ArrayList;
import java.util.List;
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
    public List<Product> getProducts(){
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findProductById(String id){
        return products.get(id);
    }

    @Override
    public List<Product> findProductByName(String productName){
        List<Product> searchedProducts = new ArrayList<>();

        for(Product product : getProducts()){
            if(product.name().toLowerCase().contains(productName.toLowerCase()))
                searchedProducts.add(product);
        }

        return searchedProducts;
    }

    @Override
    public List<Product> findProductByCategory(ProductCategory productCategory){
        List<Product> searchedProduct = new ArrayList<>();

        for(Product product : getProducts()){
            if(product.category() == productCategory)
                searchedProduct.add(product);
        }
        return searchedProduct;
    }
}
