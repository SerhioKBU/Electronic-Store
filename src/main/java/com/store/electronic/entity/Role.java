package com.store.electronic.entity;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)

public class Role extends BaseEntity {
    private RoleEnum roleName;

    public Role(){
    }

    public Role(RoleEnum name) {
        this.roleName = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name=" + roleName +
                '}';
    }
}
