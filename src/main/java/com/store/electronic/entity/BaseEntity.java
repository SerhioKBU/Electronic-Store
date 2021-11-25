package com.store.electronic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BaseEntity {
    private Integer id;

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public BaseEntity() {
    }
}
