package org.enigma.service;
import org.enigma.model.Store;
import org.enigma.model.Transaction;
import org.enigma.repository.TransactionRepository;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class TransactionService implements ITransactionService{
    private final TransactionRepository transactionRepository;
    private StoreService storeService;

    public TransactionService(TransactionRepository transactionRepository, StoreService storeService) {
        this.transactionRepository = transactionRepository;
        this.storeService = storeService;

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
            Optional<Store> find = storeService.findId(create.getStoreId());
            find.get().setStock(find.get().getStock()-create.getQty());
            storeService.update(find.get(), create.getStoreId());
            System.out.println("Transaction Added");
            return transactionRepository.create(create);
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
            transactionRepository.update(update,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id){
        try {
            transactionRepository.delete(id);
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
