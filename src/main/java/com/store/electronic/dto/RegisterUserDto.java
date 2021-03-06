package com.store.electronic.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterUserDto implements BaseDto{
    String login;
    String password;
}
