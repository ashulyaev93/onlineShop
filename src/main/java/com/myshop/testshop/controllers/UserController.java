package com.myshop.testshop.controllers;

import com.myshop.testshop.dto.UserDTO;
import com.myshop.testshop.entities.User;
import com.myshop.testshop.exeptions.UserExistException;
import com.myshop.testshop.mappers.UserMapper;
import com.myshop.testshop.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService=userService;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<Object> updateUser(@Validated @RequestBody UserDTO userDTO) throws UserExistException {//change with stream

        User user = userService.updateUser(userDTO);
        UserDTO updateUser = UserMapper.INSTANCE.userToUserDTO(user);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> usersList = userService.getAllUsers();

        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId")String userId){
        User user = userService.getUserById(Long.parseLong(userId));
        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
        try{
            userService.deleteUser(userId);
        }catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("User with id = " + userId + " deleted!");
    }

}
