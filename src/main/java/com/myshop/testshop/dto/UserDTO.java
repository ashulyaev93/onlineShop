package com.myshop.testshop.dto;

import com.myshop.testshop.entities.Order;
import com.myshop.testshop.entities.enums.Role;
import com.myshop.testshop.entities.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private Role role;
    private Status status;
    private Set<Order> orders;
    private LocalDateTime createdDate;
}
