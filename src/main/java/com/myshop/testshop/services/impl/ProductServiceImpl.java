package com.myshop.testshop.services.impl;

import com.myshop.testshop.dao.ProductDAO;
import com.myshop.testshop.dto.ProductDTO;
import com.myshop.testshop.entities.Product;
import com.myshop.testshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        productDAO.deleteById(productId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productDAO.findById(productId).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.findAll();
    }
}
