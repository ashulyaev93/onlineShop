package com.myshop.testshop.services;

import com.myshop.testshop.dto.OrderDTO;
import com.myshop.testshop.dto.OrderProductDTO;
import com.myshop.testshop.entities.Order;

import java.util.List;

public interface OrderService {

    public Order updateOrder(OrderDTO orderDTO);
    public Order createOrder(OrderDTO orderDTO);
    public void deleteOrder(Long orderId);
    public Order getOrderById(Long orderId);
    public List<Order> getAllOrders();
    public void addProducts(OrderProductDTO orderProductDTO);
}
