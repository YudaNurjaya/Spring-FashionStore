package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Price {
    private String id;
    private Double price;
    private String productId;

    public Price(Double price, String productId) {
        this.price = price;
        this.productId = productId;
    }

    public Price() {
    }

    @Override
    public String toString() {
        return "Price{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", productId='" + productId + '\'' +
                '}';
    }
}
