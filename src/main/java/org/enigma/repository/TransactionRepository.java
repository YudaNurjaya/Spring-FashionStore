package org.enigma.repository;
import org.enigma.model.Transaction;
import org.enigma.model.mapping.TransactionMapper;
import org.enigma.utils.RandomUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
public class TransactionRepository implements ITransactionRepository {
    @Autowired
    private RandomUuid randomUuid;
    private final JdbcTemplate jdbcTemplate;
    private final String SQL_GET_ALL = "select * from transaction";
    private final String INSERT_INTO_TRANSACTION = "insert into transaction values(?,?,?,?)";
    private final String SQL_FIND_BY_ID = "select * from transaction where id = ?";
    private final String SQL_UPDATE = "update transaction set date = ?, qty = ?, stock_id = ? where id = ?";
    private final String SQL_DELETE = "delete from transaction where id = ?";
    private final String SQL_FIND_BY_DATE = "select * from transaction where date = ?";

    public TransactionRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Transaction> getAll(){
        try {
           return jdbcTemplate.query(SQL_GET_ALL,new TransactionMapper());
        }catch (DataAccessException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Transaction create(Transaction create) throws Exception {
        try {
            create.setId(randomUuid.random());
            int add = jdbcTemplate.update(INSERT_INTO_TRANSACTION,create.getId(),create.getDate(),create.getQty(),create.getStoreId());
            if(add<=0){
                System.out.println("Failed to insert");
            }
            return create;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Transaction> findById(String id) throws Exception {
        try {
            Transaction transaction = jdbcTemplate.queryForObject(SQL_FIND_BY_ID,new TransactionMapper(), new Object[]{id});
            return Optional.ofNullable(transaction);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Transaction update, String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_UPDATE,update.getDate(),update.getQty(),update.getStoreId(),id);
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
    public Optional<Transaction> findByDate(Date date) throws Exception{
        try {
            Transaction transaction = jdbcTemplate.queryForObject(SQL_FIND_BY_DATE, new TransactionMapper(), new Object[]{date});
            return Optional.ofNullable(transaction);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
