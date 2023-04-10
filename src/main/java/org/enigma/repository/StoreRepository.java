package org.enigma.repository;

import org.enigma.model.Store;
import org.enigma.model.mapping.StoreMapper;
import org.enigma.utils.RandomUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StoreRepository implements IStoreRepository {
    @Autowired
    private RandomUuid randomUuid;
    private JdbcTemplate jdbcTemplate;
    private final String SQL_GET_ALL = "select * from store";
    private final String INSERT_INTO_STORE = "insert into store values(?,?,?,?,?,?)";
    private final String SQL_FIND_BY_ID = "select * from store where id = ?";
    private final String SQL_UPDATE = "update store set name = ?, size = ?, stock = ?, category = ?, price = ? where id = ?";
    private final String SQL_DELETE = "delete from store where id = ?";

    public StoreRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Store> getAll(){
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new StoreMapper());
        }catch (DataAccessException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Store create(Store create) throws Exception {
        try {
            create.setId(randomUuid.random());
            int add = jdbcTemplate.update(INSERT_INTO_STORE,create.getId(), create.getName(),create.getSize(),create.getStock(),create.getCategory(),create.getPrice());
            if(add<=0){
                throw new Exception("Failed to insert");
            }
            return create;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Store> findById(String id) throws Exception {
        try {
            Store store = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new StoreMapper(),new Object[]{id});
            return Optional.ofNullable(store);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Store update, String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_UPDATE, update.getName(),update.getSize(),update.getStock(),update.getCategory(),update.getPrice(),id);
        }catch (DataAccessException e){
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
    public List<Store> findByCategory(String category) throws Exception {
        try {
            List<Store> list = jdbcTemplate.query(SQL_GET_ALL, (rs, rowNum)-> new Store
                    (rs.getString("id"),rs.getString("name"),rs.getString("size"),
                            rs.getInt("stock"),rs.getString("category"),rs.getDouble("price")))
                    .stream()
                    .filter(c->c.getCategory().equals(category))
                    .collect(Collectors.toList());
            if(list.isEmpty()){
                System.out.println("Category not found");
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Store> findByProduct(String product) throws Exception {
        try {
            List<Store> list = jdbcTemplate.query(SQL_GET_ALL, (rs, rowNum)-> new Store
                            (rs.getString("id"),rs.getString("name"),rs.getString("size"),
                                    rs.getInt("stock"),rs.getString("category"),rs.getDouble("price")))
                    .stream()
                    .filter(c->c.getName().equals(product))
                    .collect(Collectors.toList());
            if(list.isEmpty()){
                System.out.println("Product not found");
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Store> findBySize(String size) throws Exception {
        try {
            List<Store> list = jdbcTemplate.query(SQL_GET_ALL, (rs, rowNum)-> new Store
                            (rs.getString("id"),rs.getString("name"),rs.getString("size"),
                                    rs.getInt("stock"),rs.getString("category"),rs.getDouble("price")))
                    .stream()
                    .filter(c->c.getSize().equals(size))
                    .collect(Collectors.toList());
            if(list.isEmpty()){
                System.out.println("Size not found");
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Store> findByStock(Integer stock) throws Exception {
        try {
            List<Store> list = jdbcTemplate.query(SQL_GET_ALL, (rs, rowNum)-> new Store
                            (rs.getString("id"),rs.getString("name"),rs.getString("size"),
                                    rs.getInt("stock"),rs.getString("category"),rs.getDouble("price")))
                    .stream()
                    .filter(c->c.getStock().equals(stock))
                    .collect(Collectors.toList());
            if(list.isEmpty()){
                System.out.println("Stock not found");
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Store> findByPrice(Double price) throws Exception {
        try {
            List<Store> list = jdbcTemplate.query(SQL_GET_ALL, (rs, rowNum)-> new Store
                            (rs.getString("id"),rs.getString("name"),rs.getString("size"),
                                    rs.getInt("stock"),rs.getString("category"),rs.getDouble("price")))
                    .stream()
                    .filter(c->c.getPrice().equals(price))
                    .collect(Collectors.toList());
            if(list.isEmpty()){
                System.out.println("Price not found");
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
