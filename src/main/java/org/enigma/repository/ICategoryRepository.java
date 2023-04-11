package org.enigma.repository;

import org.enigma.model.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> getAll() throws Exception;
    Category create(Category create) throws Exception;
    void update(Category update, String id) throws Exception;
    void delete(String id) throws Exception;
}
