package org.enigma.controller;

import org.enigma.configuration.BeanConfiguration;
import org.enigma.model.Category;
import org.enigma.service.ICategoryService;
import org.enigma.view.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class CategoryController {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    ICategoryService categoryService = ctx.getBean(ICategoryService.class);
    Scanner scanner = new Scanner(System.in);

    public void categoryMenu(){
        System.out.println("=== Category Menu ===");
        System.out.println("1.Add category");
        System.out.println("2.Update category");
        System.out.println("3.Delete category");
        System.out.println("4.Data category");
        System.out.println("5.Back to Main Menu");
        System.out.print("Choose\t: ");

        String input = scanner.next();
        while(!input.matches("[1-5]")){
            System.out.println("Menu not found");
            System.out.println("1.Add category");
            System.out.println("2.Update category");
            System.out.println("3.Delete category");
            System.out.println("4.Data category");
            System.out.println("5.Back to Main Menu");
            System.out.print("Choose\t: ");

            input = scanner.next();
        }
        switch (input){
            case "1" -> addCategory();
            case "2" -> updateCategory();
            case "3" -> deleteCategory();
            case "4" -> getAll();
            case "5" -> new MainMenu().mainMenu();
        }
    }

    public void addCategory(){
        System.out.println("1. Input category\t: ");
        String category = scanner.next();
        while(!category.matches("[a-zA-Z]{4,}")){
            System.out.println("Category Invalid");
            System.out.println("1. Input category\t: ");
            category = scanner.next();
        }
        Category create = new Category(category);
        categoryService.create(create);
    }

    public void updateCategory(){
        System.out.println("1. Input category Id\t: ");
        String categoryId = scanner.next();

        while(categoryId.isEmpty()){
            System.out.println("Must fill category Id");
            System.out.println("1. Input category Id\t: ");
            categoryId = scanner.next();
        }
        System.out.println("1. Input category\t: ");
        String category = scanner.next();
        while(!category.matches("[a-zA-Z]{4,}")){
            System.out.println("Category Invalid");
            System.out.println("1. Input category\t: ");
            category = scanner.next();
        }
        Category update = new Category(category);
        categoryService.update(update,categoryId);
    }

    public void deleteCategory(){
        System.out.println("1. Input category Id\t: ");
        String categoryId = scanner.next();

        while(categoryId.isEmpty()){
            System.out.println("Must fill category Id");
            System.out.println("1. Input category Id\t: ");
            categoryId = scanner.next();
        }
        categoryService.delete(categoryId);
    }

    public void getAll(){
        categoryService.getAll();
    }
}
