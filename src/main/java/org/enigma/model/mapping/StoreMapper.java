package org.enigma.model.mapping;

import org.enigma.model.Store;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreMapper implements RowMapper<Store> {
    @Override
    public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
        Store store = new Store();
        store.setId(rs.getString("id"));
        store.setName(rs.getString("name"));
        store.setSize(rs.getString("size"));
        store.setCategory(rs.getString("category"));
        store.setStock(rs.getInt("stock"));
        store.setPrice(rs.getDouble("price"));

        return store;
    }
}
