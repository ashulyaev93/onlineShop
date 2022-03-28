package com.myshop.testshop.dto;

import lombok.Data;

@Data
public class OrderProductDTO
{
    private Long productId;
    private Long orderId;
    private Double quantity;
}
