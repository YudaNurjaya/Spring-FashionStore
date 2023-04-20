package org.enigma.model.mapping;

import org.enigma.model.Stock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockMapper implements RowMapper<Stock> {
    @Override
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stock stock = new Stock();
        stock.setId(rs.getString("id"));
        stock.setStock(rs.getInt("stock"));

        return stock;
    }
}
