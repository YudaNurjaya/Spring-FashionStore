package org.enigma.repository;

import org.enigma.model.Stock;
import org.enigma.model.mapping.StockMapper;
import org.enigma.utils.RandomUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class StockRepository implements IStockRepository{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RandomUuid randomUuid;
    private final String SQL_GET_ALL = "select * from stock";
    private final String INSERT_INTO_STOCK = "insert into stock values(?,?)";
    private final String SQL_UPDATE = "update stock set stock = ? where id = ?";
    private final String SQL_DELETE = "delete from stock where id = ?";
    private final String SQL_FIND_BY_ID = "select * from stock where id = ?";

    public StockRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Stock> getAll() throws Exception {
        try {
            return jdbcTemplate.query(SQL_GET_ALL,new StockMapper());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Stock create(Stock create) throws Exception {
        try {
            create.setId(randomUuid.random());
            int add = jdbcTemplate.update(INSERT_INTO_STOCK, create.getId(),create.getStock());
            if(add<=0){
                System.out.println("Failed to insert");
            }
            return create;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Stock update, String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_UPDATE,update.getStock(),id);
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
    public Optional<Stock> findId(String id) throws Exception {
        try {
            Stock stock = jdbcTemplate.queryForObject(SQL_FIND_BY_ID,new StockMapper(),new Object[]{id});
            return Optional.ofNullable(stock);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
