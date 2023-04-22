package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String id;
    private String name;
    private String size;
    private String categoryId;
    private String stockId;


    public Product(String name, String size, String categoryId,String stockId) {
        this.name = name;
        this.size = size;
        this.categoryId = categoryId;
        this.stockId = stockId;
    }

    public Product(String id, String name, String size, String categoryId, String stockId) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.categoryId = categoryId;
        this.stockId = stockId;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", stockId='" + stockId + '\'' +
                '}';
    }
}
