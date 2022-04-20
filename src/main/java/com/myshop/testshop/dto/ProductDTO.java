package com.myshop.testshop.dto;

import com.myshop.testshop.entities.Order;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class ProductDTO {

    private Long id;
    private String title;
    private Double price;
    private Double storageQuantity;
    private String measure;
    private LocalDateTime createdDate;
    private Set<Order> orders;
}
