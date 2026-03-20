package com.olujobii.presentation;

import com.olujobii.enums.ProductCategory;
import com.olujobii.model.Product;
import com.olujobii.service.ProductService;
import com.olujobii.util.InputValidatorUtil;

import java.util.*;

public class InventoryManagementSystem {
    private final Scanner scanner;
    private final ProductService productService;

    public InventoryManagementSystem(ProductService productService){
        this.scanner = new Scanner(System.in);
        this.productService = productService;
    }

    public void startApplication(){
        System.out.println("=====INVENTORY MANAGEMENT SYSTEM=====");
        System.out.println("Welcome to the Inventory Management System 😊");

        boolean isProgramRunning = true;
        while(isProgramRunning){
            printMenu();

            System.out.print("Choose an option: ");
            String userOption = scanner.nextLine().trim();

            if(userOption.isBlank()){
                System.out.println("You did not select any option, try again");
                continue;
            }
            switch(userOption){
                case "1":
                    addProduct();
                    break;
                case "2":
                    updateProductQuantity();
                    break;
                case "3":
                    searchForProductByName();
                    break;
                case "4":
                    listByProductCategory();
                    break;
                case "5":
                    sortProductByPrice();
                    break;
                case "6":
                    removeProduct();
                    break;
                case "7":
                    calculateTotalInventoryValue();
                    break;
                case "8":
                    exitApplication();
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

    private void addProduct(){
        //VALIDATE EACH FIELD
        String id = createProductId();
        String productName = validateProductName();
        double price = validateProductPrice();
        int quantity = validateProductQuantity();
        ProductCategory productCategory = validateProductCategory();

        //CREATE PRODUCT
        Product product = productService.createNewProduct(id,productName,price,quantity,productCategory);
        System.out.println("Product created: "+product);

        //ADD TO MAP
        productService.addProduct(product);
        System.out.println("Product added successfully");
    }

    private void updateProductQuantity(){
        String productId = validateProductId();

        Product product = productService.getProduct(productId);

        if(product == null){
            System.out.println("No record of product in inventory");
            return;
        }

        System.out.println("Fetching Product for you.....");
        System.out.println(product);

        //VALIDATING NEW QUANTITY
        int quantity = validateProductQuantity();

        //Updating product
        Product updatedProduct = productService.updateProductQuantity(product,quantity);

        //Adding product to Map
        productService.addProduct(updatedProduct);
        System.out.println("Product updated successfully.");
        System.out.println(updatedProduct);
    }

    private void searchForProductByName(){
        String productName = validateProductName();

        List<Product> products = productService.searchProductName(productName);
        listProductByCriteria(products);
    }

    private void listByProductCategory(){
        //Getting the user's choice
        ProductCategory choice = validateProductCategory();

        List<Product> productList = productService.listByCategory(choice);

        listProductByCriteria(productList);
    }

    private void sortProductByPrice(){
        Map<String, Product> maps = productService.getAllProducts();

        if(maps.isEmpty()){
            System.out.println("There is no product recorded in the inventory");
            return;
        }

        List<Product> products = new ArrayList<>(maps.values());

        Collections.sort(products);
        System.out.println(products);
    }

    private void removeProduct(){
        String productId = validateProductId();

        Product product = productService.removeProduct(productId);

        if(product == null){
            System.out.println("No record of product in inventory");
            return;
        }

        System.out.println("Product removed successfully: ");
        System.out.println(product);
    }

    private void calculateTotalInventoryValue(){
        Map<String, Product> maps = productService.getAllProducts();

        if(maps.isEmpty()){
            System.out.println("No record of product in the inventory");
            return;
        }

        double totalInventoryValue = productService.totalInventoryValue();

        System.out.println("TOTAL INVENTORY VALUE: "+totalInventoryValue+" NAIRA");
    }

    private void exitApplication(){
        System.out.println("Exiting...");
        scanner.close();
    }

    //This ID operation is to validate the ID user passed
    private String validateProductId(){
        String productId;
        boolean isValid = false;

        do {
            System.out.print("Enter product ID: ");
            productId = scanner.nextLine();

            if(productId.isBlank()){
                System.out.println("You did not input any ID, please input an ID to confirm if the product exists");
                continue;
            }

            isValid = true;
        }while(!isValid);

        return productId;
    }

    //This ID operation is for creating ID for users when they are adding product
    private String createProductId(){
        String userInput;
        String id = "";
        boolean isValidationValid = false;

        do {
            System.out.print("Enter a product ID (only numbers and input should not be more then 3 digits): ");
            userInput = scanner.nextLine().trim();

            if (!InputValidatorUtil.isAValidInteger(userInput)) {
                System.out.println("Not a valid digit");
                continue;
            }

            if (userInput.length() > 3) {
                System.out.println("Not more than 3 digits");
                continue;
            }

            //Formatting ID
            id = "PR-" + userInput; //id variable

            //Checking if ID exists in Map
            boolean isIdAvailable = productService.isKeyAvailable(id);

            if (isIdAvailable) {
                System.out.println("ID exists. You have to use a unique ID");
                continue;
            }

            isValidationValid = true;
        }while(!isValidationValid);

        return id;
    }

    private String validateProductName(){
        String productName;
        boolean isValidationValid = false;
        do {
            System.out.print("Enter product name: ");
            productName = scanner.nextLine().trim();

            if (productName.isBlank()) {
                System.out.println("Not a valid product name");
                continue;
            }

            isValidationValid = true;
        }while(!isValidationValid);

        return productName;
    }

    private double validateProductPrice(){
        String userInput;
        double price = 0;
        boolean isValidationValid = false;
        do {
            System.out.print("Enter a price (not less than 500): ");
            userInput = scanner.nextLine().trim();

            if (!InputValidatorUtil.isAValidInteger(userInput)) {
                System.out.println("Not a valid number");
                continue;
            }

            price = Integer.parseInt(userInput);//price variable
            if (price < 500) {
                System.out.println("The price is less than 500 Naira. This item is not worth selling");
                continue;
            }

            isValidationValid = true;
        }while(!isValidationValid);

        return price;
    }

    private int validateProductQuantity(){
        String userInput;
        int quantity = 0;
        boolean isValidationValid = false;

        do {
            System.out.print("Enter a quantity (Not less than 1): ");
            userInput = scanner.nextLine().trim();

            if (!InputValidatorUtil.isAValidInteger(userInput)) {
                System.out.println("Not a valid number");
                continue;
            }

            quantity = Integer.parseInt(userInput); //quantity variable
            if (quantity < 1) {
                System.out.println("We only sell 1 or more quantity of a product");
                continue;
            }
            isValidationValid = true;
        }while(!isValidationValid);

        return quantity;
    }

    private ProductCategory validateProductCategory(){
        String userInput;
        ProductCategory productCategory = null;
        boolean isValidationValid = false;

        do {
            System.out.println("Choose a product category: ");
            ProductCategory[] productCategories = ProductCategory.values();

            for (int i = 0; i < productCategories.length; i++) {
                int order = i + 1;
                System.out.println(order + ". " + productCategories[i]);
            }

            System.out.print("Choose an option: ");
            userInput = scanner.nextLine().trim();

            if (!InputValidatorUtil.isAValidInteger(userInput)) {
                System.out.println("Not a valid option");
                continue;
            }

            int userProductValue = Integer.parseInt(userInput); // Product category value;

            //Check if user choice is not less than 0 or not more than length of array
            if (InputValidatorUtil.isUserOptionNotValid(userProductValue, productCategories.length)) {
                System.out.println("Not a valid option, try again");
                continue;
            }

            int index = userProductValue - 1;
            productCategory = productCategories[index];
            isValidationValid = true;
        }while(!isValidationValid);

        return productCategory;
    }

    private void listProductByCriteria(List<Product> products){
        if(products.isEmpty()){
            System.out.println("No record of product in inventory");
            return;
        }

        System.out.println("Product found, loading product details...");
        for(Product product : products)
            System.out.println(product);
    }
}
