package com.myshop.testshop.security.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UsernameRequest
{
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
