package com.myshop.testshop.services.impl;

import com.myshop.testshop.repositories.OrderRepository;
import com.myshop.testshop.repositories.ProductRepository;
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

    private OrderRepository orderRepository;
    private EntityManagerFactory entityManagerFactory;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/shop";
    private static final String USER = "postgres";
    private static final String PASS = "135246";

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EntityManagerFactory entityManagerFactory){
        this.orderRepository = orderRepository;
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

            orderRepository.save(order);
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

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void addProducts(OrderProductDTO orderProductDTO)
    {
        String sql = "INSERT INTO products_orders (product_id, order_id, quantity, priceForQuantity) VALUES (?, ?, ?, null)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, orderProductDTO.getProductId());
            preparedStatement.setLong(2, orderProductDTO.getOrderId());
            preparedStatement.setDouble(3, orderProductDTO.getQuantity());

            int rowsAdded = preparedStatement.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("A product was added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "UPDATE products_orders SET priceforquantity = quantity*(Select price FROM products where product_id = ?) where product_id = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);

            preparedStatement1.setLong(1, orderProductDTO.getProductId());
            preparedStatement1.setLong(2, orderProductDTO.getProductId());
            preparedStatement1.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "UPDATE orders SET total_price=(Select sum(priceforquantity) FROM products_orders where products_orders.order_id = ?) where order_id = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

            preparedStatement2.setLong(1, orderProductDTO.getOrderId());
            preparedStatement2.setLong(2, orderProductDTO.getOrderId());
            preparedStatement2.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String sql4 = "UPDATE products SET storage_quantity=storage_quantity-(Select quantity FROM products_orders where products_orders.product_id = ? and products_orders.order_id = ?) where product_id = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);

            preparedStatement4.setLong(1, orderProductDTO.getProductId());
            preparedStatement4.setLong(2, orderProductDTO.getOrderId());
            preparedStatement4.setDouble(3, orderProductDTO.getProductId());
            preparedStatement4.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Long productId, Long orderId)
    {
        String sql3 = "UPDATE products SET storage_quantity=storage_quantity+(Select quantity FROM products_orders where products_orders.product_id = ? and products_orders.order_id = ?) where product_id = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);

            preparedStatement3.setLong(1, productId);
            preparedStatement3.setLong(2, orderId);
            preparedStatement3.setDouble(3, productId);
            preparedStatement3.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "DELETE FROM products_orders WHERE product_id=? AND order_id=?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "UPDATE orders SET total_price=(Select sum(priceforquantity) FROM products_orders where products_orders.order_id = ?) where order_id = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);

            preparedStatement1.setLong(1, orderId);
            preparedStatement1.setLong(2, orderId);
            preparedStatement1.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProductForOrder(OrderProductDTO orderProductDTO)
    {
        String sql = "UPDATE products_orders SET quantity = ? WHERE product_id=? AND order_id=? ";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, orderProductDTO.getQuantity());
            preparedStatement.setLong(2, orderProductDTO.getProductId());
            preparedStatement.setLong(3, orderProductDTO.getOrderId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "UPDATE products_orders SET priceforquantity = quantity*(Select price FROM products where product_id = ?) where product_id = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);

            preparedStatement1.setLong(1, orderProductDTO.getProductId());
            preparedStatement1.setLong(2, orderProductDTO.getProductId());
            preparedStatement1.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "UPDATE orders SET total_price=(Select sum(priceforquantity) FROM products_orders where products_orders.order_id = ?) where order_id = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

            preparedStatement2.setLong(1, orderProductDTO.getOrderId());
            preparedStatement2.setLong(2, orderProductDTO.getOrderId());
            preparedStatement2.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO:всё отрабатывает, но в таблице не обновляет. Запрос, который отправляется руками проходит.
        String sql3 = "UPDATE products SET storage_quantity=storage_quantity + ((Select quantity FROM products_orders where products_orders.product_id= ? AND products_orders.order_id = ?)- ? ) where product_id = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS))
        {
            PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);

            preparedStatement3.setLong(1, orderProductDTO.getProductId());
            preparedStatement3.setLong(2, orderProductDTO.getOrderId());
            preparedStatement3.setDouble(3, orderProductDTO.getQuantity());
            preparedStatement3.setLong(4, orderProductDTO.getProductId());
            int rowsUp = preparedStatement3.executeUpdate();
            if (rowsUp > 0)
            {
                System.out.println("Storage_quantity with product_id = " + orderProductDTO.getProductId() + " were update successfully!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
