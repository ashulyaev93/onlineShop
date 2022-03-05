package com.myshop.testshop.services.impl;

import com.myshop.testshop.dao.UserDAO;
import com.myshop.testshop.dto.UserDTO;
import com.myshop.testshop.entities.User;
import com.myshop.testshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO usersDAO;

    @Autowired
    public UserServiceImpl(UserDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public void deleteUser(Long userId) {
        usersDAO.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return usersDAO.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return usersDAO.findAll();
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        return null;
    }
}
