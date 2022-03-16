package com.myshop.testshop.services.impl;

import com.myshop.testshop.dao.OrderDAO;
import com.myshop.testshop.dao.ProductDAO;
import com.myshop.testshop.dto.OrderDTO;
import com.myshop.testshop.dto.OrderProductDTO;
import com.myshop.testshop.entities.Order;
import com.myshop.testshop.services.OrderService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private EntityManagerFactory entityManagerFactory;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/shop";
    private static final String USER = "postgres";
    private static final String PASS = "135246";

    private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, EntityManagerFactory entityManagerFactory){
        this.orderDAO = orderDAO;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {

        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Order order;
        if(orderDTO.getId() == null) {
            order = new Order();
            order.setCode(String.valueOf(UUID.randomUUID()));
            order.setTotalPrice(orderDTO.getTotalPrice());//create auto with help math
            order.setUser(orderDTO.getUser());

            orderDAO.save(order);
        }else {
            order = session.get(Order.class, orderDTO.getId());
            session.update(order);
        }

        transaction.commit();
        session.close();

        try{
            logger.info("Saving order {}",order.getCode());
            return order;
        }catch (Exception e){
            logger.error("Error during registration. {}", e.getMessage());
            throw new RuntimeException("The order " + order.getCode() + " already exist. Please check credentials");
        }
    }

    @Override
    public Order updateOrder(OrderDTO orderDTO) {
        Order order = getOrderById(orderDTO.getId());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setUser(orderDTO.getUser());

        return orderDAO.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderDAO.deleteById(orderId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderDAO.findById(orderId).get();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    @Override
    public void addProducts(OrderProductDTO orderProductDTO)
    {
        String sql = "INSERT INTO products_orders (product_id, order_id) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, orderProductDTO.getProductId());
            preparedStatement.setLong(2, orderProductDTO.getOrderId());

            int rowsAdded = preparedStatement.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("A product was added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Long productId, Long orderId){
        String sql = "DELETE FROM products_orders WHERE product_id=? AND order_id=?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //цена каждого добавленного продукта в заказ должна суммироваться и в totalPrice должна вернуться сумма, в одно и то же время
//    private Double mathPrice(OrderDTO orderDTO, ProductDTO productDTO){
//        for (int i = 0; i < orderDTO.getProduct().size(); i++)
//        {
//            Double s;
//
//        }
//        return null;
//    }
}
