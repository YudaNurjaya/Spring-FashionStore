package org.enigma.service;

import org.enigma.model.Product;
import org.enigma.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

public class ProductService implements IStoreService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> list() {
        try {
            List<Product> list = repository.getAll();
            if(list.isEmpty()){
                System.out.println("Data is empty");
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product create(Product product) {
        try {
            System.out.println("Product Added");
            return repository.create(product);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Product update, String id) {
        try {
            System.out.println("Product Updated");
            repository.update(update,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Product> find = repository.findById(id);
            if(find.isEmpty()){
                System.out.println("Id not found");
            }else {
                System.out.println("Product Deleted");
                repository.delete(id);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> findId(String id) {
        try {
            return repository.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Product> findByProduct(String product) {
        try {
            return repository.findByProduct(product);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Product> findBySize(String size) {
        try {
            return repository.findBySize(size);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
