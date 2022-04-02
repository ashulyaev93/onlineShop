package com.myshop.testshop.security.payload.request;

import com.myshop.testshop.annotations.PasswordMatches;
import com.myshop.testshop.annotations.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Please enter you name")
    private String name;
    @NotEmpty(message = "Please enter you lastname")
    private String lastname;
    @NotEmpty(message = "Please enter you username")
    private String login;
    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}
