package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Store {
    private String id;
    private String name;
    private String size;
    private Integer stock;
    private String category;
    private Double price;

    public Store(String id, String name, String size, Integer stock, String category, Double price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.stock = stock;
        this.category = category;
        this.price = price;
    }

    public Store(String name, String size, Integer stock, String category, Double price) {
        this.name = name;
        this.size = size;
        this.stock = stock;
        this.category = category;
        this.price = price;
    }

    public Store() {
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
