package org.enigma.service;

import org.enigma.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Category create(Category create);
    void update(Category update, String id);
    void delete(String id);
}
