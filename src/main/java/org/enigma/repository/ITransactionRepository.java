package org.enigma.repository;

import org.enigma.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {
    List<Transaction> getAll() throws Exception;
    Transaction create(Transaction create) throws Exception;
    Optional<Transaction> findById(String id) throws Exception;
    void update(Transaction update, String id) throws Exception;
    void delete(String id) throws Exception;
    Optional<Transaction> findByDate(Date date) throws Exception;
}
