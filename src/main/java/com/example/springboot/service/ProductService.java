package com.example.springboot.service;


import com.example.springboot.data.dto.ProductDto;
import com.example.springboot.data.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
