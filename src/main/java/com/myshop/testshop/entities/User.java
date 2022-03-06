package com.myshop.testshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myshop.testshop.entities.enums.Role;
import com.myshop.testshop.entities.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(name = "username", length = 64, unique = true)
    private String username;
    @Column(name = "firstname", length = 64)
    private String firstname;
    @Column(name = "lastname", length = 64)
    private String lastname;
    @Column(name = "password", length = 3000, updatable = false)
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", length = 128, updatable = false)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", length = 128, updatable = false)
    private Status status;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
    @JsonFormat(pattern = "yyyy-MM-dd'HH:mm:ss'")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
