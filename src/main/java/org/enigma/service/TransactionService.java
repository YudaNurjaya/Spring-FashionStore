package org.enigma.service;
import org.enigma.model.Price;
import org.enigma.model.Product;
import org.enigma.model.Stock;
import org.enigma.model.Transaction;
import org.enigma.repository.TransactionRepository;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class TransactionService implements ITransactionService{
    private final TransactionRepository transactionRepository;
    private StockService stockService;
    private PriceService priceService;
    private ProductService productService;

    public TransactionService(TransactionRepository transactionRepository, StockService stockService, PriceService priceService, ProductService productService) {
        this.transactionRepository = transactionRepository;
        this.stockService = stockService;
        this.priceService = priceService;
        this.productService = productService;

    }

    @Override
    public List<Transaction> getAll(){
        try {
         List<Transaction> list = transactionRepository.getAll();
         if(list.isEmpty()){
             System.out.println("Data is empty");
         }
         return list;
        }catch (DataAccessException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Transaction create(Transaction create){
        try {
            Optional<Price> findPriceId = priceService.findId(create.getPriceId());
            Optional<Product> findProductId = productService.findId(findPriceId.get().getProductId());
            Optional<Stock> findStockId = stockService.findId(findProductId.get().getStockId());
            if(findStockId.get().getStock()< create.getQty()){
                System.out.println("Stock of "  + findProductId.get().getName() + " is " + findStockId.get().getStock());
                System.out.println("Cannot add transaction because out of stock");
            }
            else{
                findStockId.get().setStock(findStockId.get().getStock() - create.getQty());
                stockService.update(findStockId.get(),findProductId.get().getStockId());
                System.out.println("Transaction Added");
                transactionRepository.create(create);
            }
            return null;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Transaction> findById(String id){
        try {
            return transactionRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Transaction update, String id){
        try {
            System.out.println("Transaction Updated");
            transactionRepository.update(update,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id){
        try {
            Optional<Transaction> find = transactionRepository.findById(id);
            if(find.isEmpty()){
                System.out.println("Id not found");
            }else {
                System.out.println("Transaction Deleted");
                transactionRepository.delete(id);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Transaction> findByDate(Date date) {
        try {
            return transactionRepository.findByDate(date);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
