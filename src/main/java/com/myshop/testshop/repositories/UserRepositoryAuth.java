package com.myshop.testshop.repositories;

import com.myshop.testshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryAuth extends JpaRepository<User, Long>
{
    Optional<User> findByLogin(String login);
}
