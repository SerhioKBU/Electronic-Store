package com.store.electronic.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class Account extends BaseEntity{
    private String login;
    private String password;
    private LocalDateTime createTime;
    private Role role;

    public Account() {
    }

    public Account(Integer id, String login, String password, Role role) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
