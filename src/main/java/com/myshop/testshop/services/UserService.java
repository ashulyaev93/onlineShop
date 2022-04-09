package com.myshop.testshop.services;

import com.myshop.testshop.dto.UserDTO;
import com.myshop.testshop.entities.User;
import com.myshop.testshop.security.payload.request.SignupRequest;

import java.util.List;

public interface UserService {

    public User updateUser(UserDTO userDTO);
    public User createUser(SignupRequest signupRequest);
    public void deleteUser(Long userId);
    public User getUserById(Long userId);
    public List<User> getAllUsers();
}
