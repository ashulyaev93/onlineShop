package com.myshop.testshop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private Long id;
    private String title;
    private Double price;
    private LocalDateTime createdDate;
}
