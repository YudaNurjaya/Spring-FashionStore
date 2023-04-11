package org.enigma.service;

import org.enigma.model.Product;

import java.util.List;
import java.util.Optional;

public interface IStoreService {
    List<Product> list();
    Product create(Product create);
    void update(Product update, String id);
    void delete(String id);
    Optional<Product> findId(String id);
    List<Product> findByProduct(String product);
    List<Product> findBySize(String size);

}
