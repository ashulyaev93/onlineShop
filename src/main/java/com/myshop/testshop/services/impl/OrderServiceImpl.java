package com.myshop.testshop.services.impl;

import com.myshop.testshop.dao.OrderDAO;
import com.myshop.testshop.dto.OrderDTO;
import com.myshop.testshop.entities.Order;
import com.myshop.testshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    @Override
    public Order updateOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderDAO.deleteById(orderId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderDAO.findById(orderId).get();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }
}
