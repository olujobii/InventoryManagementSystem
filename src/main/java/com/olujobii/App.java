package com.olujobii;


import com.olujobii.presentation.InventoryManagementSystem;
import com.olujobii.repository.impl.InventoryRepositoryImpl;

import java.util.HashMap;

public class App
{
    public static void main( String[] args )
    {
        InventoryRepositoryImpl inventoryRepository = new InventoryRepositoryImpl(new HashMap<>());

        InventoryManagementSystem inventoryManagementSystem = new InventoryManagementSystem();
        inventoryManagementSystem.startApplication();
    }
}
