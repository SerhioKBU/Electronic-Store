package com.store.electronic.entity;

import lombok.Data;

import java.util.Objects;

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

    public Category() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}


