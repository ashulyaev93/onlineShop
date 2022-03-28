package com.myshop.testshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(name = "title", length = 128, unique = true)
    private String title;
    @Column(name = "price")
    private Double price;//цена за шт/кг
    @Column(name = "storage_quantity")
    private Double storageQuantity;
    @Column(name = "measure")
    private String measure;
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
