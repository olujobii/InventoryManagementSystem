package com.olujobii;


import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;
import com.olujobii.presentation.InventoryManagementSystem;
import com.olujobii.repository.InventoryRepository;
import com.olujobii.repository.impl.InventoryRepositoryImpl;
import com.olujobii.service.ProductService;
import com.olujobii.service.impl.ProductServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        Product product = new Product("PR-1","Phone",500.0,5, ProductCategory.ELECTRONICS);
        Product product2 = new Product("PR-2","Phone",800.0,5, ProductCategory.ELECTRONICS);
        Product product3 = new Product("PR-3","Phone",600.0,5, ProductCategory.ELECTRONICS);
        Map<String,Product> map = new HashMap<>();
        map.put(product.id(), product);
        map.put(product2.id(), product2);
        map.put(product3.id(), product3);

        InventoryRepository inventoryRepository = new InventoryRepositoryImpl(map);
        ProductService productService = new ProductServiceImpl(inventoryRepository);

        InventoryManagementSystem inventoryManagementSystem = new InventoryManagementSystem(productService);
        inventoryManagementSystem.startApplication();
    }
}
