package org.enigma.view;

import org.enigma.controller.*;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);

    public void mainMenu(){
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Category Menu");
            System.out.println("2. Product Menu");
            System.out.println("3. Stock Menu");
            System.out.println("4. Price Menu");
            System.out.println("5. Transaction Menu");
            System.out.println("6. Report Menu");
            System.out.println("7. Exit");
            System.out.print("Choose\t: ");

            String input = scanner.next();
            while(!input.matches("[1-7]")){
                System.out.println("Menu not found");
                System.out.println("=== Main Menu ===");
                System.out.println("1. Category Menu");
                System.out.println("2. Product Menu");
                System.out.println("3. Stock Menu");
                System.out.println("4. Price Menu");
                System.out.println("5. Transaction Menu");
                System.out.println("6. Report Menu");
                System.out.println("7. Exit");
                System.out.print("Choose\t: ");

                input = scanner.next();
            }
            switch (input) {
                case "1" -> new CategoryController().categoryMenu();
                case "2" -> new ProductController().productMenu();
                case "3" -> new StockController().stockMenu();
                case "4" -> new PriceController().priceMenu();
                case "5" -> new TransactionController().transactionMenu();
                case "6" -> new ReportController().reportMenu();
                case "7" -> System.exit(0);
            }
        }
    }
}
