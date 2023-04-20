package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Stock {
    private String id;
    private Integer stock;

    public Stock(Integer stock) {
        this.stock = stock;
    }

    public Stock() {
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id='" + id + '\'' +
                ", stock=" + stock +
                '}';
    }
}
