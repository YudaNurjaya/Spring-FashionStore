package org.enigma.model.mapping;

import org.enigma.model.Price;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceMapper implements RowMapper<Price> {
    @Override
    public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
        Price price = new Price();
        price.setId(rs.getString("id"));
        price.setPrice(rs.getDouble("price"));
        price.setProductId(rs.getString("product_id"));
        price.setStockId(rs.getString("stock_id"));
        return price;
    }
}
