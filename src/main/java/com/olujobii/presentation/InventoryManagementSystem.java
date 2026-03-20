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
        String userInput;
        String id = "";
        String productName = "";
        double price = 0;
        int quantity = 0;
        ProductCategory productCategory = null;
        boolean isValidationValid = false;

        //VALIDATING PRODUCT ID - FIRST WHILE LOOP
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

        isValidationValid = false;
        //VALIDATING PRODUCT NAME - SECOND WHILE LOOP
        do {
            System.out.print("Enter product name: ");
            userInput = scanner.nextLine().trim();

            if (userInput.isBlank()) {
                System.out.println("Not a valid product name");
                continue;
            }

            productName = userInput;//product name variable
            isValidationValid = true;
        }while(!isValidationValid);


        //VALIDATING PRICE - THIRD WHILE LOOP
        isValidationValid = false;
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


        //VALIDATING QUANTITY - FOURTH WHILE LOOP
        isValidationValid = false;
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


        //VALIDATING PRODUCT CATEGORY - FINAL WHILE LOOP
        isValidationValid = false;
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


        Product product = productService.createNewProduct(id,productName,price,quantity,productCategory);
        System.out.println("Product created: "+product);

        productService.addProduct(product);
        System.out.println("Product added successfully");
    }

    private void updateProductQuantity(){
        String userInput = "";
        String productId = "";
        boolean isValid = false;
        int quantity = 0;
        //CHECKING IF ID EXISTS
        do {
            System.out.print("Enter product ID: ");
            productId = scanner.nextLine();

            if(productId.isBlank()){
                System.out.println("You did not input any ID, please input an ID to confirm if the product exists");
                continue;
            }

            isValid = true;
        }while(!isValid);

        Product product = productService.getProduct(productId);

        if(product == null){
            System.out.println("Product does not exist in our system");
            return;
        }

        System.out.println("Fetching Product for you.....");
        System.out.println(product);

        //VALIDATING NEW QUANTITY
        isValid = false;
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
            isValid = true;
        }while(!isValid);

        //Updating product
        Product updatedProduct = productService.updateProductQuantity(product,quantity);

        //Adding product to Map
        productService.addProduct(updatedProduct);
        System.out.println("Product updated successfully.");
        System.out.println(updatedProduct);
    }

    private void searchForProductByName(){
        String productName = "";
        boolean isValid = false;
        do {
            System.out.print("Enter product name: ");
            productName = scanner.nextLine().trim();

            if (productName.isBlank()) {
                System.out.println("Not a valid product name");
                continue;
            }

            isValid = true;
        }while(!isValid);

        List<Product> products = productService.searchProductName(productName);
        if(products.isEmpty()){
            System.out.println("Product does not exist in our system");
            return;
        }

        System.out.println("Product found, loading product details...");
        for(Product product : products)
            System.out.println(product);
    }

    private void listByProductCategory(){
        String userInput = "";
        int productCategoryChoice = 0;
        boolean isValid = false;
        ProductCategory[] productCategories = ProductCategory.values();

        do {
            for (int i = 0; i < productCategories.length; i++) {
                int order = i + 1;
                System.out.println(order + ". " + productCategories[i]);
            }

            System.out.print("Choose an option: ");
            userInput = scanner.nextLine().trim();

            if(userInput.isBlank()){
                System.out.println("You did not choose any option, try again");
                continue;
            }

            if(!InputValidatorUtil.isAValidInteger(userInput)){
                System.out.println("Not a valis option");
                continue;
            }

            productCategoryChoice = Integer.parseInt(userInput);

            if(InputValidatorUtil.isUserOptionNotValid(productCategoryChoice,productCategories.length)){
                System.out.println("You did not choose an option that is specified");
                continue;
            }

            isValid = true;
        }while(!isValid);

        int index = productCategoryChoice - 1;
        //Getting the user's choice

        ProductCategory productCategory = ProductCategory.values()[index];
        List<Product> productList = productService.listByCategory(productCategory);

        if(productList.isEmpty()){
            System.out.println("No product is available for this category");
            return;
        }

        for(Product product: productList){
            System.out.println(product);
        }
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
        String productId = "";
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

        Product product = productService.removeProduct(productId);

        if(product == null){
            System.out.println("Product does not exist in our system.");
            return;
        }

        System.out.println("Product removed successfully: ");
        System.out.println(product);
    }

    private void calculateTotalInventoryValue(){
        Map<String, Product> maps = productService.getAllProducts();

        if(maps.isEmpty()){
            System.out.println("There is no product recorded in the inventory");
            return;
        }

        double totalInventoryValue = productService.totalInventoryValue();

        System.out.println("TOTAL INVENTORY VALUE: "+totalInventoryValue+" NAIRA");
    }

    private void exitApplication(){
        System.out.println("Exiting...");
        scanner.close();
    }
}
