package org.enigma.repository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportRepository {
    private JdbcTemplate jdbcTemplate;
    private final String SQL_REPORT_ALL = "select s.id, t.date, s.name, s.category, s.price, t.qty, sum(s.price*t.qty) as total from store as s join transaction as t on s.id = t.store_id group by s.id, t.date, s.name, s.category, s.price, t.qty";
    private final String SQL_REPORT_DAILY = "select s.id, t.date, s.name, s.category, s.price, t.qty, sum(s.price*t.qty) as total from store as s join transaction as t on s.id = t.store_id  where t.date = ? group by s.id, t.date, s.name, s.category, s.price, t.qty";
    private final String SQL_REPORT_MONTHLY = "select s.id, t.date, s.name, s.category, s.price, t.qty, sum(s.price*t.qty) as total from store as s join transaction as t on s.id = t.store_id  where t.date between ? and ? group by s.id, t.date, s.name, s.category, s.price, t.qty";
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
