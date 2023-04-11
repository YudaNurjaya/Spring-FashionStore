package org.enigma.repository;

import org.enigma.model.Store;

import java.util.List;
import java.util.Optional;

public interface IStoreRepository {
    List<Store> getAll() throws Exception;
    Store create(Store create) throws Exception;
    Optional<Store> findById(String id) throws Exception;
    void update(Store update, String id) throws Exception;
    void delete(String id) throws Exception;
    List<Store> findByProduct(String product) throws Exception;
    List<Store> findBySize(String size) throws Exception;

}
