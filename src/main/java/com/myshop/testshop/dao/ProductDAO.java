package com.myshop.testshop.dao;

import com.myshop.testshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDAO extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
