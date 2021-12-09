package com.store.electronic.authorisation;

import com.store.electronic.entity.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AuthUser {
    private RoleEnum role;
    private String login;

//    enum AuthRole {
//        USER, ADMIN;
//    }
}
