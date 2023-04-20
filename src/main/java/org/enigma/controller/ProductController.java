package org.enigma.controller;

import org.enigma.configuration.BeanConfiguration;
import org.enigma.model.Product;
import org.enigma.service.IStoreService;
import org.enigma.view.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class ProductController {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    IStoreService storeService = ctx.getBean(IStoreService.class);
    Scanner scanner = new Scanner(System.in);

    public void productMenu(){
        while(true) {
            System.out.println("=== Product Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Search Product");
            System.out.println("5. Data Product");
            System.out.println("6. Back to Main Menu");
            System.out.println("7. Exit");
            System.out.print("Choose\t: ");

            String input = scanner.next();
            while (!input.matches("[1-7]")) {
                System.out.println("Menu not found");
                System.out.println("=== Store Menu ===");
                System.out.println("1. Add Product");
                System.out.println("2. Update Product");
                System.out.println("3. Delete Product");
                System.out.println("4. Search Product");
                System.out.println("5. Data Product");
                System.out.println("6. Back to Main Menu");
                System.out.println("7. Exit");
                System.out.print("Choose\t: ");
                input = scanner.next();
            }

            switch (input) {
                case "1" -> addProduct();
                case "2" -> updateProduct();
                case "3" -> deleteProduct();
                case "4" -> searchProduct();
                case "5" -> getAll();
                case "6" -> new MainMenu().mainMenu();
                case "7" -> System.exit(0);
            }
        }
    }
    public void addProduct(){
        System.out.println("=== Add Product ===");
        System.out.println("1. Name of product\t: ");
        String product = scanner.next();
        while (!product.matches("^[a-zA-Z0-9]+${4,}")){
            System.out.println("Name invalid");
            System.out.println("1. Name of product\t: ");
            product = scanner.nextLine();
        }
        System.out.println("2. Size\t\t: ");
        String size = scanner.next();
        while(!size.matches("([Xx][Ss]|[SsMmLlXx]{1,2}|[Xx][Ll])|[0-9]{1,2}")){
            System.out.println("Size invalid");
            System.out.println("2. Size\t\t: ");
            size = scanner.next();
        }
        System.out.println("3. Category\t: ");
        String category = scanner.next();
        while(category.matches("[a-zA-Z]")){
            System.out.println("4. Category\t: ");
            category = scanner.next();
        }
        System.out.println("4. Input stock id\t: ");
        String stockId = scanner.next();
        while(stockId.isEmpty()){
            System.out.println("Must fill stock Id");
            System.out.println("2. Input stock id\t: ");
            stockId = scanner.next();
        }
        Product store = new Product(product,size,category,stockId);
        storeService.create(store);
    }
    public void updateProduct(){
        System.out.println("=== Update Product ===");
        System.out.println("Input Id Product\t: ");
        String id = scanner.next();
        while (id.isEmpty()){
            System.out.println("Id not valid");
            System.out.println("Input Id Product\t: ");
            id = scanner.next();
        }
        System.out.println("1. Name of product\t: ");
        String product = scanner.nextLine();
        while (!product.matches("^[a-zA-Z0-9]+${4,}")){
            System.out.println("Name invalid");
            System.out.println("1. Name of product\t: ");
            product = scanner.nextLine();
        }
        System.out.println("2. Size\t\t: ");
        String size = scanner.next();
        while(!size.matches("([Xx][Ss]|[SsMmLlXx]{1,2}|[Xx][Ll])|[0-9]{1,2}")){
            System.out.println("Size invalid");
            System.out.println("2. Size\t\t: ");
            size = scanner.next();
        }
        System.out.println("3. Category Id\t: ");
        String category = scanner.next();
        while(category.matches("[a-zA-Z]")){
            System.out.println("4. Category\t: ");
            category = scanner.next();
        }
        System.out.println("4. Input stock id\t: ");
        String stockId = scanner.next();
        while(stockId.isEmpty()){
            System.out.println("Must fill stock Id");
            System.out.println("2. Input stock id\t: ");
            stockId = scanner.next();
        }
        Product store = new Product(product,size,category,stockId);
        storeService.update(store,id);
    }

    public void deleteProduct(){
        System.out.println("Input Id Product\t: ");
        String id = scanner.next();
        while (id.isEmpty()){
            System.out.println("Id not valid");
            System.out.println("Input Id Product\t: ");
            id = scanner.next();
        }
        storeService.delete(id);
    }
    public void searchProduct(){
        System.out.println("Search by\t: ");
        String by = scanner.next();
        if(by.equalsIgnoreCase("id")){
            System.out.println("Input Id Product\t: ");
            String id = scanner.next();
            while (id.isEmpty()){
                System.out.println("Id not valid");
                System.out.println("Input Id Product\t: ");
                id = scanner.next();
            }
            storeService.findId(id).stream().forEach(System.out::println);
        }

        if(by.equalsIgnoreCase("size")){
            System.out.println("Input Size\t: ");
            String size = scanner.next();
            while(!size.matches("([Xx][Ss]|[SsMmLlXx]{1,2}|[Xx][Ll])|[0-9]{1,2}")){
                System.out.println("Size invalid");
                System.out.println("Input Size\t: ");
                size = scanner.next();
            }
            storeService.findBySize(size).stream().forEach(System.out::println);
        }
        if(by.equalsIgnoreCase("product")){
            System.out.println("Input Product\t: ");
            String product = scanner.next();
            while(!product.matches("^[a-zA-Z0-9]+${4,}")){
                System.out.println("Product Invalid");
                System.out.println("Input Product\t: ");
                product = scanner.next();
            }
            storeService.findByProduct(product).stream().forEach(System.out::println);
        }
        else{
            System.out.println("Cannt find the column for search by");
        }
    }
    public void getAll(){
        storeService.list().forEach(System.out::println);
    }
}
