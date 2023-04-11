package org.enigma.repository;

import org.enigma.model.Category;
import org.enigma.model.Price;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {
    List<Category> getAll() throws Exception;
    Category create(Category create) throws Exception;
    void update(Category update, String id) throws Exception;
    void delete(String id) throws Exception;
    Optional<Category> findId(String id) throws Exception;
}
