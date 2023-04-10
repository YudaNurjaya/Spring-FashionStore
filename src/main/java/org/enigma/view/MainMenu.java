package org.enigma.view;

import org.enigma.controller.ReportController;
import org.enigma.controller.StoreController;
import org.enigma.controller.TransactionController;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);

    public void mainMenu(){
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Store Menu");
            System.out.println("2. Transaction Menu");
            System.out.println("3. Report Menu");
            System.out.println("4. Exit");
            System.out.print("Choose\t: ");

            String input = scanner.next();
            while(!input.matches("[1-4]")){
                System.out.println("Menu not found");
                System.out.println("=== Main Menu ===");
                System.out.println("1. Store Menu");
                System.out.println("2. Transaction Menu");
                System.out.println("3. Report Menu");
                System.out.println("4. Exit");
                System.out.print("Choose\t: ");

                input = scanner.next();
            }
            switch (input) {
                case "1" -> new StoreController().storeMenu();
                case "2" -> new TransactionController().transactionMenu();
                case "3" -> new ReportController().reportMenu();
                case "4" -> System.exit(0);
            }
        }
    }
}
