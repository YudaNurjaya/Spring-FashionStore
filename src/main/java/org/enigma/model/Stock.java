package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Stock {
    private String id;
    private Integer stock;
    private String productId;

    public Stock(Integer stock,String productId) {
        this.stock = stock;
        this.productId = productId;
    }

    public Stock() {
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id='" + id + '\'' +
                ", stock=" + stock +
                ", productId='" + productId + '\'' +
                '}';
    }
}
