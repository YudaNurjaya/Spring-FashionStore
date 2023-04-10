package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter @Setter
public class Transaction {
    private String id;
    private LocalDate date;
    private Integer qty;
    private String storeId;



    public Transaction(LocalDate date, Integer qty, String storeId) {
        this.date = date;
        this.qty = qty;
        this.storeId = storeId;
    }

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", qty=" + qty +
                ", storeId='" + storeId + '\'' +
                '}';
    }
}
