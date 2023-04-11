package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Store {
    private String id;
    private String name;
    private String size;
    private String categoryId;


    public Store(String id, String name, String size, String categoryId) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.categoryId = categoryId;
    }

    public Store(String name, String size, String categoryId) {
        this.name = name;
        this.size = size;
        this.categoryId = categoryId;
    }

    public Store() {
    }
}
