package com.myshop.testshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(name = "code", length = 64, unique = true, updatable = false)
    private String code;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @JsonIgnore
    @ManyToMany(mappedBy = "orders")
    private List<Product> product;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
