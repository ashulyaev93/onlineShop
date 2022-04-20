package com.myshop.testshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationDTO
{
    private String username;
    private String password;
}
