package org.enigma.model.mapping;

import org.enigma.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getString("id"));
        transaction.setDate(LocalDate.parse(rs.getString("date")));
        transaction.setQty(rs.getInt("qty"));
        transaction.setPriceId(rs.getString("price_id"));

        return transaction;
    }
}
