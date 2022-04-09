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
    private String email;
    private Role role;
    private Status status;
    private Set<Order> orders;
    private LocalDateTime createdDate;

//    public void setPassword(String password) {
//        this.password = passwordEncoder().encode(password);
//    }
//
//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
}
