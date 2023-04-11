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
    private final String SQL_GET_ALL = "select * from product";
    private final String INSERT_INTO_STORE = "insert into product values(?,?,?,?)";
    private final String SQL_FIND_BY_ID = "select * from product where id = ?";
    private final String SQL_UPDATE = "update product set name = ?, size = ?, category_id = ? where id = ?";
    private final String SQL_DELETE = "delete from product where id = ?";

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
            int add = jdbcTemplate.update(INSERT_INTO_STORE,create.getId(), create.getName(),create.getSize(),create.getCategoryId());
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
            jdbcTemplate.update(SQL_UPDATE, update.getName(),update.getSize(),update.getCategoryId(),id);
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
    public List<Store> findByProduct(String product) throws Exception {
        try {
            List<Store> list = jdbcTemplate.query(SQL_GET_ALL, (rs, rowNum)-> new Store
                            (rs.getString("id"),rs.getString("name"),rs.getString("size"),
                                    rs.getString("category_id")))
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
                                    rs.getString("category_id")))
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
}
