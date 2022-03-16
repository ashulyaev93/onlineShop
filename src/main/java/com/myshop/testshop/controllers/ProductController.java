package com.myshop.testshop.controllers;

import com.myshop.testshop.dto.ProductDTO;
import com.myshop.testshop.entities.Product;
import com.myshop.testshop.mappers.ProductMapper;
import com.myshop.testshop.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService){
        this.productService=productService;
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@Validated @RequestBody ProductDTO productDTO) {

        Product product = productService.createProduct(productDTO);
        ProductDTO createProduct = ProductMapper.INSTANCE.productToProductDTO(product);

        return new ResponseEntity<>(createProduct, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@Validated @RequestBody ProductDTO productDTO){

        Product product = productService.updateProduct(productDTO);
        ProductDTO updateProduct = ProductMapper.INSTANCE.productToProductDTO(product);

        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productsList = productService.getAllProduct();

        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId")String productId){
        Product product = productService.getProductById(Long.parseLong(productId));
        ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(product);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId){
        try{
            productService.deleteProduct(productId);
        }catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("Product with id = " + productId + " deleted!");
    }
}
