package com.myshop.testshop.controllers;

import com.myshop.testshop.dto.UserDTO;
import com.myshop.testshop.entities.Order;
import com.myshop.testshop.entities.User;
import com.myshop.testshop.entities.enums.Role;
import com.myshop.testshop.entities.enums.Status;
import com.myshop.testshop.mappers.UserMapper;
import com.myshop.testshop.services.impl.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserController.class)
public class UserControllerTest {

    private static UserServiceImpl userService = mock(UserServiceImpl.class);

    UserController userController = new UserController(
            userService
    );

    @Test
    public void updateUserTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .username("alex93")
                .firstname("alex")
                .lastname("shul")
                .password("12345")
                .email("alex@mail.ru")
                .role(Role.ADMIN)
                .status(Status.ACTIVE)
                .orders(orders)
                .createdDate(localDateTime).build();

        User user = UserMapper.INSTANCE.userDTOtoUser(userDTO);
        userDTO.setLastname("shulyaev");
        when(userService.updateUser(userDTO)).thenReturn(user);

        Assert.assertEquals(userController.updateUser(userDTO).getStatusCodeValue(), 200);
    }

    @Test
    public void getAllUsersTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        User user = User.builder()
                .id(1L)
                .username("alex93")
                .firstname("alex")
                .lastname("shul")
                .password("12345")
                .email("alex@mail.ru")
                .role(Role.ADMIN)
                .status(Status.ACTIVE)
                .orders(orders)
                .createdDate(localDateTime).build();

        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userService.getAllUsers()).thenReturn(userList);

        Assert.assertEquals(userController.getAllUsers().getStatusCodeValue(), 200);
    }

    @Test
    public void getUserProfileTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        User user = User.builder()
                .id(1L)
                .username("alex93")
                .firstname("alex")
                .lastname("shul")
                .password("12345")
                .email("alex@mail.ru")
                .role(Role.ADMIN)
                .status(Status.ACTIVE)
                .orders(orders)
                .createdDate(localDateTime).build();

        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userService.getUserById(user.getId())).thenReturn(user);

        Assert.assertEquals(userController.getUserProfile("1").getStatusCodeValue(), 200);
    }

    @Test
    public void deleteUserTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        User user = User.builder()
                .id(1L)
                .username("alex93")
                .firstname("alex")
                .lastname("shul")
                .password("12345")
                .email("alex@mail.ru")
                .role(Role.ADMIN)
                .status(Status.ACTIVE)
                .orders(orders)
                .createdDate(localDateTime).build();

        Assert.assertEquals(userController.deleteUser(user.getId()).getStatusCodeValue(), 200);
    }
}
