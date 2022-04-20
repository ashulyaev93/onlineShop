package com.myshop.testshop.controllers;

import com.myshop.testshop.dto.OrderDTO;
import com.myshop.testshop.entities.Order;
import com.myshop.testshop.entities.Product;
import com.myshop.testshop.entities.User;
import com.myshop.testshop.mappers.OrderMapper;
import com.myshop.testshop.services.impl.OrderServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = OrderController.class)
public class OrderControllerTest {

    private static OrderServiceImpl orderService = mock(OrderServiceImpl.class);

    OrderController orderController = new OrderController(
            orderService
    );

    @Test
    public void updateOrderTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        List<Product> productList = new ArrayList<>();
        OrderDTO orderDTO = OrderDTO.builder()
                .id(1L)
                .code("code1")
                .totalPrice(112.5)
                .createdDate(localDateTime)
                .user(new User())
                .product(productList).build();

        Order order = OrderMapper.INSTANCE.orderDTOtoOrder(orderDTO);
        orderDTO.setTotalPrice(0.0);

        when(orderService.updateOrder(orderDTO)).thenReturn(order);
        Assert.assertEquals(orderController.updateOrder(orderDTO).getStatusCodeValue(), 200);
    }

    @Test
    public void getAllOrdersTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        List<Product> productList = new ArrayList<>();
        Order order = Order.builder()
                .id(1L)
                .code("code1")
                .totalPrice(112.5)
                .createdDate(localDateTime)
                .user(new User())
                .product(productList).build();

        List<Order> orderList = new ArrayList();
        orderList.add(order);
        when(orderService.getAllOrders()).thenReturn(orderList);

        Assert.assertEquals(orderController.getAllOrders().getStatusCodeValue(), 200);
    }

    @Test
    public void getOrderTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        List<Product> productList = new ArrayList<>();
        Order order = Order.builder()
                .id(1L)
                .code("code1")
                .totalPrice(112.5)
                .createdDate(localDateTime)
                .user(new User())
                .product(productList).build();

        when(orderService.getOrderById(order.getId())).thenReturn(order);
        Assert.assertEquals(orderController.getOrder("1").getStatusCodeValue(), 200);
    }

    @Test
    public void deleteOrderTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        List<Product> productList = new ArrayList<>();
        Order order = Order.builder()
                .id(1L)
                .code("code1")
                .totalPrice(112.5)
                .createdDate(localDateTime)
                .user(new User())
                .product(productList).build();

        Assert.assertEquals(orderController.deleteOrder(order.getId()).getStatusCodeValue(), 200);
    }

    @Test
    public void addProducTest(){

    }

    @Test
    public void deleteProductFromOrderTest(){
        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        List<Product> productList = new ArrayList<>();
        Order order = Order.builder()
                .id(1L)
                .code("code1")
                .totalPrice(112.5)
                .createdDate(localDateTime)
                .user(new User())
                .product(productList).build();


    }

    @Test
    public void updateProductFromOrderTest(){

    }
}
