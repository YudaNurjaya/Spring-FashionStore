package org.enigma.repository;

import org.enigma.model.Category;
import org.enigma.model.mapping.CategoryMapper;
import org.enigma.utils.RandomUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CategoryRepository implements ICategoryRepository{
    @Autowired
    private RandomUuid randomUuid;
    private JdbcTemplate jdbcTemplate;
    private final String SQL_GET_ALL = "select * from category";
    private final String INSERT_INTO_STORE = "insert into category values(?,?)";
    private final String SQL_UPDATE = "update category set category = ? where id = ?";
    private final String SQL_DELETE = "delete from category where id = ?";

    public CategoryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Category> getAll(){
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new CategoryMapper());
        }catch (DataAccessException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Category create(Category create){
        try {
            create.setId(randomUuid.random());
            int add = jdbcTemplate.update(INSERT_INTO_STORE,create.getId(),create.getCategory());
            if(add<=0){
                System.out.println("Failed to insert");
            }
            return create;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Category update, String id){
        try {
            jdbcTemplate.update(SQL_UPDATE,update.getCategory(),id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id){
        try {
            jdbcTemplate.update(SQL_DELETE,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
