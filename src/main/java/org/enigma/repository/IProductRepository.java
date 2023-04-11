package org.enigma.repository;

import org.enigma.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> getAll() throws Exception;
    Product create(Product create) throws Exception;
    Optional<Product> findById(String id) throws Exception;
    void update(Product update, String id) throws Exception;
    void delete(String id) throws Exception;
    List<Product> findByProduct(String product) throws Exception;
    List<Product> findBySize(String size) throws Exception;

}
