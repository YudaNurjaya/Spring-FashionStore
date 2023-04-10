package org.enigma.service;

import org.enigma.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    List<Transaction> getAll();
    Transaction create(Transaction create);
    Optional<Transaction> findById(String id);
    void update(Transaction update, String id);
    void delete(String id);
    Optional<Transaction> findByDate(Date date);
}
