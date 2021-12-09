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

    public Product(Integer id, String name, int cost, Category category, int quantity) {
        super(id);
        this.cost = cost;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }
}
