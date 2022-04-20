package com.myshop.testshop.controllers;

import com.myshop.testshop.dto.ProductDTO;
import com.myshop.testshop.entities.Order;
import com.myshop.testshop.entities.Product;
import com.myshop.testshop.mappers.ProductMapper;
import com.myshop.testshop.services.impl.ProductServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = ProductController.class)
public class ProductControllerTest {

    private static ProductServiceImpl productService = mock(ProductServiceImpl.class);

    ProductController productController = new ProductController(
            productService
    );

    @Test
    public void addProductTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .title("banan")
                .price(123.1)
                .storageQuantity(11.5)
                .measure("kg")
                .createdDate(localDateTime)
                .orders(orders).build();

        Assert.assertEquals(productController.addProduct(productDTO).getStatusCodeValue(), 200);
    }

    @Test
    public void updateProductTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .title("banan")
                .price(123.1)
                .storageQuantity(11.5)
                .measure("kg")
                .createdDate(localDateTime)
                .orders(orders).build();

        Product product = ProductMapper.INSTANCE.productDTOtoProduct(productDTO);
        productDTO.setPrice(512.45);

        when(productService.updateProduct(productDTO)).thenReturn(product);
        Assert.assertEquals(productController.updateProduct(productDTO).getStatusCodeValue(), 200);
    }

    @Test
    public void getAllProductsTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        Product product = new Product()
                .builder()
                .id(1L)
                .title("banan")
                .price(123.1)
                .storageQuantity(11.5)
                .measure("kg")
                .createdDate(localDateTime)
                .orders(orders).build();

        List<Product> productsList = new ArrayList<>();
        productsList.add(product);
        when(productService.getAllProduct()).thenReturn(productsList);

        Assert.assertEquals(productController.getAllProducts().getStatusCodeValue(), 200);
    }

    @Test
    public void deleteProductTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        Product product = new Product()
                .builder()
                .id(1L)
                .title("banan")
                .price(123.1)
                .storageQuantity(11.5)
                .measure("kg")
                .createdDate(localDateTime)
                .orders(orders).build();

        Assert.assertEquals(productController.deleteProduct(product.getId()).getStatusCodeValue(), 200);
    }

    @Test
    public void getProductTest(){

        LocalDateTime localDateTime = LocalDateTime.of(2022,04,17,22,29);
        Set<Order> orders = new HashSet<>();
        Product product = new Product()
                .builder()
                .id(1L)
                .title("banan")
                .price(123.1)
                .storageQuantity(11.5)
                .measure("kg")
                .createdDate(localDateTime)
                .orders(orders).build();

        List<Product> productsList = new ArrayList<>();
        productsList.add(product);
        when(productService.getProductById(product.getId())).thenReturn(product);

        Assert.assertEquals(productController.getProduct("1").getStatusCodeValue(), 200);
    }
}
