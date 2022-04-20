package com.myshop.testshop.dto;

import com.myshop.testshop.entities.Product;
import com.myshop.testshop.entities.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {

    private Long id;
    private String code;
    private Double totalPrice;
    private LocalDateTime createdDate;
    private User user;
    private List<Product> product;
}
