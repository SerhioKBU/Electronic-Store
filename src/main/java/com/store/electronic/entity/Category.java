package com.store.electronic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Category extends BaseEntity {
    private String name;

    public Category(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Category(Integer id) {
        super(id);
    }
}
