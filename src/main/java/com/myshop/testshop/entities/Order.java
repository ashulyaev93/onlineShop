package com.myshop.testshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(name = "code", length = 64, unique = true)
    private String code;
    @Column(name = "total_price")
    private Double totalPrice;
    @JsonFormat(pattern = "yyyy-MM-dd'HH:mm:ss'")
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @JsonIgnore
    @ManyToMany(mappedBy = "orders")
    private List<Product> product;
}
