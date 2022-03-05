package com.myshop.testshop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private String code;
    private Double totalPrice;
    private LocalDateTime createdDate;
}
