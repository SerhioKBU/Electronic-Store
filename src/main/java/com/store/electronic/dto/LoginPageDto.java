package com.store.electronic.dto;

import com.store.electronic.entity.Account;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginPageDto implements BaseDto{
    String login;
    String password;
    String userName;
    String email;
}
