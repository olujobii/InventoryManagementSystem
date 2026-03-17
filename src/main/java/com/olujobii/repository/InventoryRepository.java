package com.olujobii.repository;

import com.olujobii.model.Product;

public interface InventoryRepository {

    void add(Product product);

    void update(Product product);

    boolean searchByName(Product product);


}
