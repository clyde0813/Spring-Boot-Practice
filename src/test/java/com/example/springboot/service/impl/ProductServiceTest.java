package com.example.springboot.service.impl;


import com.example.springboot.data.dao.ProductDAO;
import com.example.springboot.data.dto.ProductDto;
import com.example.springboot.data.dto.ProductResponseDto;
import com.example.springboot.data.entity.Product;
import com.example.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {
//    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductDAO productDAO = Mockito.mock(ProductDAO.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImpl(productDAO);
    }

    @Test
    @DisplayName("getProduct Service Test")
    void getProductTest() {
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        Mockito.when(productDAO.selectProduct(123L))
                .thenReturn(givenProduct);
        ProductResponseDto productResponseDto = productService.getProduct(123L);

        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productDAO).selectProduct(123L);
    }

    @Test
    @DisplayName("saveProduct Service Test")
    void saveProductTest() {
        Mockito.when(productDAO.insertProduct(any(Product.class)))
                .then(returnsFirstArg());
        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("펜", 1000, 1234)
        );

        Assertions.assertEquals(productResponseDto.getName(), "펜");
        Assertions.assertEquals(productResponseDto.getPrice(), 1000);
        Assertions.assertEquals(productResponseDto.getStock(), 1234);

        verify(productDAO).insertProduct(any());
    }
}