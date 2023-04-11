package org.enigma.controller;

import org.enigma.configuration.BeanConfiguration;
import org.enigma.model.Transaction;
import org.enigma.service.ITransactionService;
import org.enigma.utils.GenerateDate;
import org.enigma.view.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Scanner;

public class TransactionController {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    ITransactionService transactionService = ctx.getBean(ITransactionService.class);

    Scanner scanner = new Scanner(System.in);
    public void transactionMenu(){
        while (true) {
            System.out.println("=== Transaction ===");
            System.out.println("1. Add Transaction");
            System.out.println("2. Update Transaction");
            System.out.println("3. Delete Transaction");
            System.out.println("4. Data Transaction");
            System.out.println("5. Search Transaction");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose\t: ");

            String input = scanner.next();

            while(!input.matches("[1-6]")){
                System.out.println("Menu not found");
                System.out.println("=== Transaction ===");
                System.out.println("1. Add Transaction");
                System.out.println("2. Update Transaction");
                System.out.println("3. Delete Transaction");
                System.out.println("4. Data Transaction");
                System.out.println("5. Search Transaction");
                System.out.println("6. Back to Main Menu");
                System.out.print("Choose\t: ");

                input = scanner.next();
            }
            switch (input) {
                case "1" -> addTransaction();
                case "2" -> updateTransaction();
                case "3" -> deleteTransaction();
                case "4" -> getAll();
                case "5" -> searchTransaction();
                case "6" -> new MainMenu().mainMenu();
            }
        }
    }

    public void addTransaction(){
        System.out.println("=== Add Transaction ===");
        System.out.println("1. Input date\t: ");
        String date = scanner.next();
        while(!date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")){
            System.out.println("Format Date is invalid, must be like yyyy-MM-dd");
            System.out.println("1. Input date\t: ");
            date = scanner.nextLine();
        }

        System.out.println("2. Input quantity\t: ");
        int qty = scanner.nextInt();
        while(qty<=0){
            System.out.println("Quantity invalid");
            System.out.println("2. Input quantity\t: ");
            qty = scanner.nextInt();
        }

        System.out.println("3. Input price id\t: ");
        String priceId = scanner.next();
        while(priceId.isEmpty()){
            System.out.println("Price Id cannot empty");
            System.out.println("3. Input product id\t: ");
            priceId = scanner.next();
        }

        Transaction transaction = new Transaction(LocalDate.parse(date),qty,priceId);
        transactionService.create(transaction);
    }

    public void updateTransaction(){
        System.out.println("=== Update Transaction ===");
        System.out.println("Input Transaction Id\t: ");
        String id = scanner.next();
        while(id.isEmpty()){
            System.out.println("Must fill id");
            System.out.println("Input Transaction Id\t: ");
            id = scanner.next();
        }

        System.out.println("1. Input date\t: ");
        String date = scanner.next();
        while(!date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")){
            System.out.println("Format Date is invalid, must be like yyyy-MM-dd");
            System.out.println("1. Input date\t: ");
            date = scanner.nextLine();
        }

        System.out.println("2. Input quantity\t: ");
        int qty = scanner.nextInt();
        while(qty<=0){
            System.out.println("Quantity invalid");
            System.out.println("2. Input quantity\t: ");
            qty = scanner.nextInt();
        }

        System.out.println("3. Input price id\t: ");
        String priceId = scanner.next();
        while(priceId.isEmpty()){
            System.out.println("Price Id cannot empty");
            System.out.println("3. Input price id\t: ");
            priceId = scanner.next();
        }

        Transaction transaction = new Transaction(LocalDate.parse(date),qty,priceId);
        transactionService.update(transaction,id);
    }

    public void deleteTransaction(){
        System.out.println("=== Delete Transaction ===");
        System.out.println("Input Transaction Id\t: ");
        String id = scanner.next();
        while(id.isEmpty()){
            System.out.println("Must fill id");
            System.out.println("Input Transaction Id\t: ");
            id = scanner.next();
        }
        transactionService.delete(id);
    }

    public void getAll(){
        transactionService.getAll().forEach(System.out::println);
    }

    public void searchTransaction(){
        System.out.println("=== Search Transaction ===");
        System.out.println("1.Id");
        System.out.println("2.date");
        System.out.println("Search by\t: ");

        String input = scanner.next();
        while(!(input.equalsIgnoreCase("id") || input.equalsIgnoreCase("date"))){
            System.out.println("Wrong Input");
            System.out.println("Search by\t: ");
            input = scanner.next();
        }
        if(input.equalsIgnoreCase("id")){
            System.out.println("Input Transaction Id\t: ");
            String id = scanner.next();

            while(id.isEmpty()){
                System.out.println("Id cannot empty");
                System.out.println("Input Transaction Id\t: ");
                id = scanner.next();
            }
            transactionService.findById(id).stream().forEach(System.out::println);
        }
        if(input.equalsIgnoreCase("date")){
            System.out.println("Input Date\t: ");
            String date = scanner.next();

            while(!date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")){
                System.out.println("Format date must be like yyyy-MM-dd");
                System.out.println("Input Date\t: ");
                date = scanner.next();
            }
            transactionService.findByDate(GenerateDate.generate(date)).stream().forEach(System.out::println);
        }
    }
}
