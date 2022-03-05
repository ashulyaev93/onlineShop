package com.myshop.testshop.services;

import com.myshop.testshop.dto.UserDTO;
import com.myshop.testshop.entities.User;

import java.util.List;

public interface UserService {

    public User updateUser(UserDTO userDTO);
    public User createUser(UserDTO userDTO);
    public void deleteUser(Long userId);
    public User getUserById(Long userId);
    public List<User> getAllUsers();
}
