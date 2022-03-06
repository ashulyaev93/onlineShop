package com.myshop.testshop.dto;

import com.myshop.testshop.entities.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private String code;
    private Double totalPrice;
    private LocalDateTime createdDate;
    private UserDTO user;
    private List<Product> product;
}
