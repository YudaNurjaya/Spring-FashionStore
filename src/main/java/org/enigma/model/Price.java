package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Price {
    private String id;
    private Double price;
    private String productId;
    private String stockId;

    public Price(Double price, String productId, String stockId) {
        this.price = price;
        this.productId = productId;
        this.stockId = stockId;
    }

    public Price() {
    }


    @Override
    public String toString() {
        return "Price{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", productId='" + productId + '\'' +
                ", stockId='" + stockId + '\'' +
                '}';
    }
}
