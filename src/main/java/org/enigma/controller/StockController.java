package org.enigma.controller;

import org.enigma.configuration.BeanConfiguration;
import org.enigma.model.Stock;
import org.enigma.service.IStockService;
import org.enigma.view.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class StockController {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    IStockService stockService = ctx.getBean(IStockService.class);
    Scanner scanner = new Scanner(System.in);

    public void stockMenu() {
        while (true) {
            System.out.println("=== Stock Menu ===");
            System.out.println("1.Add Stock");
            System.out.println("2.Update Stock");
            System.out.println("3.Delete Stock");
            System.out.println("4.Data Stock");
            System.out.println("5.Back to Main Menu");
            System.out.print("Choose\t: ");

            String input = scanner.next();

            while(!input.matches("[1-5]")){
                System.out.println("=== Stock Menu ===");
                System.out.println("1.Add Stock");
                System.out.println("2.Update Stock");
                System.out.println("3.Delete Stock");
                System.out.println("4.Data Stock");
                System.out.println("5.Back to Main Menu");
                System.out.print("Choose\t: ");

                input = scanner.next();
            }
            switch (input) {
                case "1" -> addStock();
                case "2" -> updateStock();
                case "3" -> deleteStock();
                case "4" -> getAll();
                case "5" -> new MainMenu().mainMenu();
            }
        }
    }

    public void addStock(){
        System.out.println("1. Input stock\t: ");
        int stock = scanner.nextInt();
        while(stock<=0){
            System.out.println("Stock invalid");
            System.out.println("1. Input stock\t: ");
            stock = scanner.nextInt();
        }
        Stock create = new Stock(stock);
        stockService.create(create);
    }

    public void updateStock(){
        System.out.println("== Update Stock ===");
        System.out.println("Input Stock Id\t: ");
        String id = scanner.next();
        while (id.isEmpty()){
            System.out.println("Must fill Stock Id");
            System.out.println("Input Stock Id\t: ");
            id = scanner.next();
        }
        System.out.println("1. Input stock\t: ");
        int stock = scanner.nextInt();
        while(stock<=0){
            System.out.println("Stock invalid");
            System.out.println("1. Input stock\t: ");
            stock = scanner.nextInt();
        }
        Stock update = new Stock(stock);
        stockService.update(update,id);
    }

    public void deleteStock(){
        System.out.println("=== Delete Stock ===");
        System.out.println("Input Stock Id\t: ");
        String id = scanner.next();
        while (id.isEmpty()){
            System.out.println("Must fill Stock Id");
            System.out.println("Input Stock Id\t: ");
            id = scanner.next();
        }
        stockService.delete(id);
    }

    public void getAll(){
        stockService.getAll().forEach(System.out::println);
    }
}
