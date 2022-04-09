package com.myshop.testshop.services.impl;

import com.myshop.testshop.entities.enums.Role;
import com.myshop.testshop.entities.enums.Status;
import com.myshop.testshop.repositories.UserRepository;
import com.myshop.testshop.dto.UserDTO;
import com.myshop.testshop.entities.User;
import com.myshop.testshop.exeptions.UserExistException;
import com.myshop.testshop.security.payload.request.SignupRequest;
import com.myshop.testshop.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository usersDAO;
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserServiceImpl(UserRepository usersDAO, EntityManagerFactory entityManagerFactory) {
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
    public User createUser(SignupRequest signupRequest) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());
        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

        usersDAO.save(user);

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
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());

        return usersDAO.save(user);
    }
}
