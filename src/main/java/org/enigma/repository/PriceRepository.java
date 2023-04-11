package org.enigma.repository;
import org.enigma.model.Price;
import org.enigma.model.mapping.PriceMapper;
import org.enigma.utils.RandomUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PriceRepository implements IPriceRepository{
    @Autowired
    private RandomUuid randomUuid;
    private JdbcTemplate jdbcTemplate;
    private final String SQL_GET_ALL = "select * from price";
    private final String INSERT_INTO_STORE = "insert into price values(?,?,?,?)";
    private final String SQL_UPDATE = "update price set price = ?, product_id = ?, stock_id = ? where id = ?";
    private final String SQL_DELETE = "delete from price where id = ?";
    private final String SQL_FIND_BY_ID = "select * from price where id = ?";

    public PriceRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Price> getAll() throws Exception {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new PriceMapper());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Price create(Price create) throws Exception {
        try {
            create.setId(randomUuid.random());
            int add =  jdbcTemplate.update(INSERT_INTO_STORE, create.getId(),create.getPrice(),create.getProductId(),create.getStockId());
            if(add<=0){
                System.out.println("Failed to insert");
            }
            return create;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Price update, String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_UPDATE,update.getPrice(),update.getProductId(),update.getStockId(),id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_DELETE,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Price> findId(String id) throws Exception {
        try {
            Price price = jdbcTemplate.queryForObject(SQL_FIND_BY_ID,new PriceMapper(), new Object[]{id});
            return Optional.ofNullable(price);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
