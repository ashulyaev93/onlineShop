package com.myshop.testshop.controllers;

import com.myshop.testshop.dto.AuthenticationDTO;
import com.myshop.testshop.entities.Order;
import com.myshop.testshop.entities.User;
import com.myshop.testshop.entities.enums.Role;
import com.myshop.testshop.entities.enums.Status;
import com.myshop.testshop.repositories.UserRepositoryAuth;
import com.myshop.testshop.security.JwtTokenProvider;
import com.myshop.testshop.security.payload.request.SignupRequest;
import com.myshop.testshop.services.impl.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = AuthenticationController.class)
public class AuthenticationControllerTest {

    private static AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private static UserRepositoryAuth userRepositoryAuth = mock(UserRepositoryAuth.class);
    private static JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);
    private static UserServiceImpl userService = mock(UserServiceImpl.class);

    AuthenticationController authenticationController = new AuthenticationController(
            authenticationManager,
            userRepositoryAuth,
            jwtTokenProvider,
            userService
    );

    @Test
    public void registerUser(){

        SignupRequest signupRequest = SignupRequest.builder()
                .email("ashulyaev@mail.ru")
                .firstname("alex")
                .lastname("shulyaev")
                .username("ashul")
                .password("12345")
                .confirmPassword("12345").build();
        Assert.assertEquals(authenticationController.registerUser(signupRequest).getStatusCodeValue(), 200);
    }

//    @Test
//    public void authenticateTest(){
//
//        AuthenticationDTO request = AuthenticationDTO.builder()
//                .password("12345")
//                .username("alex93").build();
//        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
//        Set<Order> orders = new HashSet<>();
//        User user = User.builder()
//                .id(1L)
//                .username("alex93")
//                .firstname("alex")
//                .lastname("shul")
//                .password("12345")
//                .email("alex@mail.ru")
//                .role(Role.ADMIN)
//                .status(Status.ACTIVE)
//                .orders(orders)
//                .createdDate(localDateTime).build();
//
//        when(userRepositoryAuth.findByUsername("alex93").toString()).thenReturn(request.getUsername());
//        Assert.assertEquals(authenticationController.authenticate(request), 200);
//    }

//    @Test
//    public void logoutTest(){
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
//        doNothing().when(securityContextLogoutHandler.logout(request,response,null))
//        verify(securityContextLogoutHandler.logout(request, response, null));
//    }
}
