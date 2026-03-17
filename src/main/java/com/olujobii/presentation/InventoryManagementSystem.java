package com.olujobii.presentation;

import java.util.Scanner;

public class InventoryManagementSystem {
    private final Scanner scanner;

    public InventoryManagementSystem(){
        this.scanner = new Scanner(System.in);
    }

    public void startApplication(){
        System.out.println("=====INVENTORY MANAGEMENT SYSTEM=====");
        System.out.println("Welcome to the Inventory Management System 😊");

        boolean isProgramRunning = true;
        while(isProgramRunning){
            printMenu();

            System.out.print("Choose an option: ");
            String userOption = scanner.nextLine();

            switch(userOption){
                case "1":
                    System.out.println("Product added");
                    break;
                case "2":
                    System.out.println("Product updated");
                    break;
                case "3":
                    System.out.println("Product search complete");
                    break;
                case "4":
                    System.out.println("Product category listed");
                    break;
                case "5":
                    System.out.println("Product sort complete");
                    break;
                case "6":
                    System.out.println("Product removed successfully");
                    break;
                case "7":
                    System.out.println("Total Inventory calculated");
                    break;
                case "8":
                    System.out.println("Exiting...");
                    isProgramRunning = false;
                    break;
                default:
                    System.out.println("No valid option selected");
                    break;
            }

        }
    }

    private void printMenu() {
        System.out.println("What will you like to do: ");
        System.out.println("1. Add a Product");
        System.out.println("2. Update Product Quantity");
        System.out.println("3. Search for Product by Name");
        System.out.println("4. List by Product Category");
        System.out.println("5. Sort by Product Price");
        System.out.println("6. Remove a Product");
        System.out.println("7. Calculate Total Inventory Value");
        System.out.println("8. Exit");
    }
}
