package org.enigma.model.mapping;

import org.enigma.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getString("id"));
        product.setName(rs.getString("name"));
        product.setSize(rs.getString("size"));
        product.setCategoryId(rs.getString("category_id"));
        product.setStockId(rs.getString("stock_id"));

        return product;
    }
}
