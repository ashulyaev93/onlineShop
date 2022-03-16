package com.myshop.testshop.services.impl;

import com.myshop.testshop.dao.UserDAO;
import com.myshop.testshop.dto.UserDTO;
import com.myshop.testshop.entities.User;
import com.myshop.testshop.exeptions.UserExistException;
import com.myshop.testshop.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO usersDAO;
    private EntityManagerFactory entityManagerFactory;

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    @Autowired
    public UserServiceImpl(UserDAO usersDAO, EntityManagerFactory entityManagerFactory) {
        this.usersDAO = usersDAO;
        this.entityManagerFactory = entityManagerFactory;
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
    public User createUser(UserDTO userDTO) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        User user;
        if(userDTO.getId() == null) {
            user = new User();
            user.setUsername(userDTO.getUsername());
            user.setFirstname(userDTO.getFirstname());
            user.setLastname(userDTO.getLastname());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());
            user.setStatus(userDTO.getStatus());

            usersDAO.save(user);
        }else {
            user = session.get(User.class, userDTO.getId());
            session.update(user);
        }

        transaction.commit();
        session.close();

        try{
            logger.info("Saving user {}",user.getUsername());
            return user;
        }catch (Exception e){
            logger.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User user = getUserById(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setPassword(userDTO.getPassword());

        return usersDAO.save(user);
    }
}
