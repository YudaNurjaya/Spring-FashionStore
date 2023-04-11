package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter @Setter
public class Transaction {
    private String id;
    private LocalDate date;
    private Integer qty;
    private String priceId;



    public Transaction(LocalDate date, Integer qty, String priceId) {
        this.date = date;
        this.qty = qty;
        this.priceId = priceId;
    }

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", qty=" + qty +
                ", priceId='" + priceId + '\'' +
                '}';
    }
}
