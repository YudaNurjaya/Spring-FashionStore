package org.enigma.repository;

import org.enigma.model.Stock;

import java.util.List;
import java.util.Optional;

public interface IStockRepository {
    List<Stock> getAll() throws Exception;
    Stock create(Stock create) throws Exception;
    void update(Stock update, String id) throws Exception;
    void delete(String id) throws Exception;
    Optional<Stock> findId(String id)throws Exception;
}
