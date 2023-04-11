package org.enigma.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Category {
    private String id;
    private String category;

    public Category(String category) {
        this.category = category;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
