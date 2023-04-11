package org.enigma.service;

import org.enigma.model.Store;
import org.enigma.repository.StoreRepository;
import java.util.List;
import java.util.Optional;

public class StoreService implements IStoreService {
    private final StoreRepository repository;

    public StoreService(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Store> list() {
        try {
            List<Store> list = repository.getAll();
            if(list.isEmpty()){
                System.out.println("Data is empty");
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Store create(Store store) {
        try {
            System.out.println("Product Added");
            return repository.create(store);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Store update, String id) {
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
            System.out.println("Product Deleted");
            repository.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Store> findId(String id) {
        try {
            return repository.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Store> findByProduct(String product) {
        try {
            return repository.findByProduct(product);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Store> findBySize(String size) {
        try {
            return repository.findBySize(size);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
