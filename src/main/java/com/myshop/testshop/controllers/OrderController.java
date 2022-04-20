package com.myshop.testshop.controllers;

import com.myshop.testshop.dto.OrderDTO;
import com.myshop.testshop.dto.OrderProductDTO;
import com.myshop.testshop.entities.Order;
import com.myshop.testshop.mappers.OrderMapper;
import com.myshop.testshop.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> addOrder(@Validated @RequestBody OrderDTO orderDTO) {

        Order order = orderService.createOrder(orderDTO);
        OrderDTO createOrder = OrderMapper.INSTANCE.orderToOrderDTO(order);

        return new ResponseEntity<>(createOrder, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateOrder(@Validated @RequestBody OrderDTO orderDTO){

        Order order = orderService.updateOrder(orderDTO);
        OrderDTO updateOrder = OrderMapper.INSTANCE.orderToOrderDTO(order);

        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orderList = orderService.getAllOrders();

        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("orderId")String orderId){
        Order order = orderService.getOrderById(Long.parseLong(orderId));
        OrderDTO orderDTO = OrderMapper.INSTANCE.orderToOrderDTO(order);

        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId){
        try{
            orderService.deleteOrder(orderId);
        }catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("Order with id = " + orderId + " deleted!");
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@Validated @RequestBody OrderProductDTO orderProductDTO) {

        orderService.addProducts(orderProductDTO);

        return ResponseEntity.ok("Product with id = " + orderProductDTO.getProductId() + " added at order with id = " + orderProductDTO.getOrderId());
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<String> deleteProductFromOrder(@RequestParam("productId") Long productId,
                                                         @RequestParam("orderId") Long orderId){
        try{
            orderService.deleteProduct(productId,orderId);
        }catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("Product with id = " + productId + " deleted from order with id = " + orderId + "!");
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Object> updateProductFromOrder(@Validated @RequestBody OrderProductDTO orderProductDTO){

        orderService.updateProductForOrder(orderProductDTO);

        return ResponseEntity.ok("Order with id = " + orderProductDTO.getOrderId() + " updated");
    }
}
