package org.enigma.service;


import org.enigma.model.Stock;


import java.util.List;
import java.util.Optional;

public interface IStockService {
    List<Stock> getAll() ;
    Stock create(Stock create) ;
    void update(Stock update, String id) ;
    void delete(String id) ;
    Optional<Stock> findId(String id);
}
