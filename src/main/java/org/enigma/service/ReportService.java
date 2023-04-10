package org.enigma.service;

import org.enigma.repository.ReportRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportService {
    private ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public List<Map<String, Object>> report(){
        try {
            return repository.report();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Map<String, Object>> reportDaily(Date date){
        try {
            return repository.reportDaily(date);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public List<Map<String, Object>> reportMonthly(Date startDate, Date endDate){
        try {
            return repository.reportMonthly(startDate, endDate);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
