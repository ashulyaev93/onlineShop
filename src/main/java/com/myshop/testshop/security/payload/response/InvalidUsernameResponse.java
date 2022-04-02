package com.myshop.testshop.security.payload.response;

import lombok.Getter;

@Getter
public class InvalidUsernameResponse
{
    private String login;
    private String password;

    public InvalidUsernameResponse(){
        this.login = "Invalid Login";
        this.password = "Invalid Password";
    }
}
