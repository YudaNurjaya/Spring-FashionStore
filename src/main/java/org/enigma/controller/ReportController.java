package org.enigma.controller;

import org.enigma.configuration.BeanConfiguration;
import org.enigma.service.ReportService;
import org.enigma.utils.GenerateDate;
import org.enigma.view.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class ReportController {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    private ReportService reportService = ctx.getBean(ReportService.class);
    Scanner scanner = new Scanner(System.in);

    public void reportMenu() {
        while (true) {
            System.out.println("=== Report ===");
            System.out.println("1. Report Daily");
            System.out.println("2. Report Monthly");
            System.out.println("3. All Report");
            System.out.println("4. Back to Main Menu");
            System.out.println("Choose\t: ");

            String input = scanner.next();

            while(!input.matches("[1-4]")){
                System.out.println("Menu not found");
                System.out.println("1. Report Daily");
                System.out.println("2. Report Monthly");
                System.out.println("3. All Report");
                System.out.println("4. Back to Main Menu");
                System.out.println("Choose\t: ");
                input = scanner.next();
            }
            switch (input) {
                case "1" -> reportDaily();
                case "2" -> reportMonthly();
                case "3" -> report();
                case "4" -> new MainMenu().mainMenu();
            }
        }
    }

    public void reportDaily(){
        System.out.println("Input Date\t: ");
        String date = scanner.next();

        while(!date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")){
            System.out.println("Format date must be like yyyy-MM-dd");
            System.out.println("Input Date\t: ");
            date = scanner.next();
        }
        reportService.reportDaily(GenerateDate.generate(date)).forEach(System.out::println);
    }

    public void reportMonthly(){
        System.out.println("Input Start Date\t: ");
        String start = scanner.next();

        while(!start.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")){
            System.out.println("Format date must be like yyyy-MM-dd");
            System.out.println("Input Start Date\t: ");
            start = scanner.next();
        }
        System.out.println("Input End Date\t: ");
        String end = scanner.next();

        while (!end.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")){
            System.out.println("Format date must be like yyyy-MM-dd");
            System.out.println("Input End Date\t: ");
            end = scanner.next();
        }

        reportService.reportMonthly(GenerateDate.generate(start), GenerateDate.generate(end)).forEach(System.out::println);
    }

    public void report(){
        reportService.report().forEach(System.out::println);
    }
}
