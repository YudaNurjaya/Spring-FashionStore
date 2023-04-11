package org.enigma.repository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportRepository {
    private JdbcTemplate jdbcTemplate;
    private final String SQL_REPORT_ALL = "select t.date, p.product_id, p.price, t.qty, c.category, sum(p.price*t.qty) as total\n" +
            "from transaction t\n" +
            "join price p on t.price_id = p.id\n" +
            "join product pr on p.product_id = pr.id\n" +
            "join category c on pr.category_id = c.id\n" +
            "group by t.date, p.product_id, p.price, t.qty, c.category";
    private final String SQL_REPORT_DAILY = "select t.date, p.product_id, p.price, t.qty, c.category, sum(p.price*t.qty) as total\n" +
            "from transaction t\n" +
            "join price p on t.price_id = p.id\n" +
            "join product pr on p.product_id = pr.id\n" +
            "join category c on pr.category_id = c.id\n" +
            "where t.date = ?\n" +
            "group by t.date, p.product_id, p.price, t.qty, c.category";
    private final String SQL_REPORT_MONTHLY = "select t.date, p.product_id, p.price, t.qty, c.category, sum(p.price*t.qty) as total\n" +
            "from transaction t\n" +
            "join price p on t.price_id = p.id\n" +
            "join product pr on p.product_id = pr.id\n" +
            "join category c on pr.category_id = c.id\n" +
            "where t.date between ? and ?" +
            "group by t.date, p.product_id, p.price, t.qty, c.category";
    public ReportRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Map<String, Object>> report(){
        try {
            List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL_REPORT_ALL)
                    .stream()
                    .collect(Collectors.toList());
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Map<String, Object>> reportDaily(Date date){
        try {
            List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL_REPORT_DAILY,date)
                    .stream()
                    .collect(Collectors.toList());
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Map<String,Object>> reportMonthly(Date startDate, Date endDate){
        try {
            List<Map<String, Object>> list = jdbcTemplate.queryForList(SQL_REPORT_MONTHLY,startDate,endDate)
                    .stream()
                    .collect(Collectors.toList());
            return list;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
