package com.myshop.testshop.services.impl;

import com.myshop.testshop.repositories.ProductRepository;
import com.myshop.testshop.dto.ProductDTO;
import com.myshop.testshop.entities.Product;
import com.myshop.testshop.exeptions.ProductExistException;
import com.myshop.testshop.services.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private EntityManagerFactory entityManagerFactory;

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, EntityManagerFactory entityManagerFactory){
        this.productRepository = productRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Product product;
        if(productDTO.getId() == null) {
            product = new Product();
            product.setTitle(productDTO.getTitle());
            product.setPrice(productDTO.getPrice());
            product.setStorageQuantity(productDTO.getStorageQuantity());
            product.setMeasure(productDTO.getMeasure());

            productRepository.save(product);
        }else {
            product = session.get(Product.class, productDTO.getId());
            session.update(product);
        }

        transaction.commit();
        session.close();

        try{
            logger.info("Saving product {}",product.getTitle());
            return product;
        }catch (Exception e){
            logger.error("Error during registration. {}", e.getMessage());
            throw new ProductExistException("The product " + product.getTitle() + " already exist. Please check credentials");
        }
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        Product product = getProductById(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setStorageQuantity(productDTO.getStorageQuantity());
        product.setMeasure(productDTO.getMeasure());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
