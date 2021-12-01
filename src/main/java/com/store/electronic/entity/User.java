package com.store.electronic.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User extends BaseEntity{
    private String userName;
    private String email;
    private Account account;

    private Integer money;

    public User(Integer id, String userName, String email) {
        super(id);
        this.userName = userName;
        this.email = email;
    }

    public User() {
        super(null);
    }
}
