package com.store.electronic.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterUserDto extends BaseDto{
    String login;
    String password;
    String userName;
}
