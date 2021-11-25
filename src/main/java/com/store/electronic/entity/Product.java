package com.store.electronic.entity;

import lombok.Data;

@Data
public class Product extends BaseEntity {
    private int cost;
    private String name;
    private Category category;
    private Integer quantity;

    public Product() {
        super(null);
    }

    public Product(Integer id, int cost, String name, Category category) {
        super(id);
        this.cost = cost;
        this.name = name;
        this.category = category;
    }
}
