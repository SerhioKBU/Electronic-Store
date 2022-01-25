package com.store.electronic.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(chain = true)
public class User extends BaseEntity{
    private String userName;
    private String email;
    private Account account;
    private Integer money;

    public User(Integer id, Account account, String userName, String email) {
        super(id);
        this.account = account;
        this.userName = userName;
        this.email = email;
    }

    public User(Integer id, String userName, String email) {
        super(id);
        this.userName = userName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(account, user.account) && Objects.equals(money, user.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, email, account, money);
    }

    public User() {
        super(null);
    }
}
