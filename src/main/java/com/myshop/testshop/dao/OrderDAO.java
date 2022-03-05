package com.myshop.testshop.dao;

import com.myshop.testshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDAO extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
