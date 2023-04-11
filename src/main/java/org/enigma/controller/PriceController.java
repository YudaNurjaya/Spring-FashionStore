package org.enigma.controller;

import org.enigma.configuration.BeanConfiguration;
import org.enigma.model.Price;
import org.enigma.service.IPriceService;
import org.enigma.view.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class PriceController {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    IPriceService priceService = ctx.getBean(IPriceService.class);
    Scanner scanner = new Scanner(System.in);

    public void priceMenu(){
        System.out.println("=== Price Menu ===");
        System.out.println("1.Add Price");
        System.out.println("2.Update Price");
        System.out.println("3.Delete Price");
        System.out.println("4.Data Price");
        System.out.println("5.Back to Main Menu");
        System.out.print("Choose\t: ");

        String input = scanner.next();
        while(!input.matches("[1-5]")){
            System.out.println("Menu not found");
            System.out.println("1.Add Price");
            System.out.println("2.Update Price");
            System.out.println("3.Delete Price");
            System.out.println("4.Data Price");
            System.out.println("5.Back to Main Menu");
            System.out.print("Choose\t: ");

            input = scanner.next();
        }
        switch (input){
            case "1" -> addPrice();
            case "2" -> updatePrice();
            case "3" -> deletePrice();
            case "4" -> getAll();
            case "5" -> new MainMenu().mainMenu();
        }
    }

    public void addPrice(){
        System.out.println("1. Input price\t: ");
        Double price = scanner.nextDouble();
        while(price<=0){
            System.out.println("Price invalid");
            System.out.println("1. Input price\t: ");
            price = scanner.nextDouble();
        }
        System.out.println("2. Input product id\t: ");
        String productId = scanner.next();
        while(productId.isEmpty()){
            System.out.println("Must fill product Id");
            System.out.println("2. Input product id\t: ");
            productId = scanner.next();
        }
        System.out.println("2. Input stock id\t: ");
        String stockId = scanner.next();
        while(stockId.isEmpty()){
            System.out.println("Must fill stock Id");
            System.out.println("2. Input stock id\t: ");
            stockId = scanner.next();
        }
        Price create = new Price(price,productId,stockId);
        priceService.create(create);
    }

    public void updatePrice(){
        System.out.println("1. Input price Id\t: ");
        String priceId = scanner.next();
        while(priceId.isEmpty()){
            System.out.println("Must fill price Id");
            System.out.println("1. Input price Id\t: ");
            priceId = scanner.next();
        }
        System.out.println("1. Input price\t: ");
        Double price = scanner.nextDouble();
        while(price<=0){
            System.out.println("Price invalid");
            System.out.println("1. Input price\t: ");
            price = scanner.nextDouble();
        }
        System.out.println("2. Input product id\t: ");
        String productId = scanner.next();
        while(productId.isEmpty()){
            System.out.println("Must fill product Id");
            System.out.println("2. Input product id\t: ");
            productId = scanner.next();
        }
        System.out.println("2. Input stock id\t: ");
        String stockId = scanner.next();
        while(stockId.isEmpty()){
            System.out.println("Must fill stock Id");
            System.out.println("2. Input stock id\t: ");
            stockId = scanner.next();
        }
        Price update = new Price(price,productId,stockId);
        priceService.update(update,priceId);
    }

    public void deletePrice(){
        System.out.println("1. Input price Id\t: ");
        String priceId = scanner.next();
        while(priceId.isEmpty()){
            System.out.println("Must fill price Id");
            System.out.println("1. Input price Id\t: ");
            priceId = scanner.next();
        }
        priceService.delete(priceId);
    }

    public void getAll(){
        priceService.getAll();
    }
}
