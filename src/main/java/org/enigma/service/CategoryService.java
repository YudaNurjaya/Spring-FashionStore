package org.enigma.service;

import org.enigma.model.Category;
import org.enigma.repository.ICategoryRepository;


import java.util.List;
import java.util.Optional;

public class CategoryService implements ICategoryService{
    ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll(){
        try {
         return categoryRepository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Category create(Category create) {
        try {
            System.out.println("Category Created");
            return categoryRepository.create(create);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Category update, String id) {
        try {
            System.out.println("Category Updated");
            categoryRepository.update(update, id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Category> find = categoryRepository.findId(id);
            if(find.isEmpty()){
                System.out.println("Id not found");
            }else {
                System.out.println("Category Deleted");
                categoryRepository.delete(id);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
