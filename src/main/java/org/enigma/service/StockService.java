package org.enigma.service;

import org.enigma.model.Stock;
import org.enigma.repository.IStockRepository;

import java.util.List;
import java.util.Optional;

public class StockService implements IStockService{
    IStockRepository stockRepository;

    public StockService(IStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> getAll() {
        try {
            return stockRepository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Stock create(Stock create) {
        try {
            System.out.println("Stock Created");
            return stockRepository.create(create);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Stock update, String id) {
        try {
            System.out.println("Stock Updated");
            stockRepository.update(update,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            System.out.println("Stock Deleted");
            stockRepository.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Stock> findId(String id) {
        try {
            return stockRepository.findId(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
