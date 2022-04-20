package com.myshop.testshop.security.payload.response;

import lombok.Getter;

@Getter
public class InvalidUsernameResponse
{
    private String username;
    private String password;

    public InvalidUsernameResponse(){
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }
}
