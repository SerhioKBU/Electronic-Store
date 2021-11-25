package com.store.electronic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
public class Basket extends BaseEntity {
    User user;
    List<Product> products;

    public Basket(Integer id) {
        super(null);
    }

    public Basket(Integer id, User user, List<Product> products) {
        super(id);
        this.user = user;
        this.products = products;
    }
}
