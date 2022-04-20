package com.myshop.testshop.security.payload.request;

import com.myshop.testshop.annotations.PasswordMatches;
import com.myshop.testshop.annotations.ValidEmail;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@PasswordMatches
public class SignupRequest {
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Please enter you name")
    private String firstname;
    @NotEmpty(message = "Please enter you lastname")
    private String lastname;
    @NotEmpty(message = "Please enter you username")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}
