package org.enigma.service;

import org.enigma.model.Store;

import java.util.List;
import java.util.Optional;

public interface IStoreService {
    List<Store> list();
    Store create(Store create);
    void update(Store update, String id);
    void delete(String id);
    Optional<Store> findId(String id);
    List<Store> findByProduct(String product);
    List<Store> findBySize(String size);

}
