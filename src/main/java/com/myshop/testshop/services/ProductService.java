package com.myshop.testshop.services;

import com.myshop.testshop.dto.ProductDTO;
import com.myshop.testshop.entities.Product;

import java.util.List;

public interface ProductService {

    public Product updateProduct(ProductDTO productDTO);
    public Product createProduct(ProductDTO productDTO);
    public void deleteProduct(Long productId);
    public Product getProductById(Long productId);
    public List<Product> getAllProduct();
}
