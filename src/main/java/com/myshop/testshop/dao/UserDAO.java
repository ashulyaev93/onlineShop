package com.myshop.testshop.dao;

import com.myshop.testshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDAO extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
