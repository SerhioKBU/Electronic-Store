package com.store.electronic.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Basket extends BaseEntity {
    User user;
    List<Category> categories;

    public Basket(Integer id) {
        super(null);
    }

    public Basket(Integer id, User user, List<Category> categories) {
        super(id);
        this.user = user;
        this.categories = categories;
    }
}
