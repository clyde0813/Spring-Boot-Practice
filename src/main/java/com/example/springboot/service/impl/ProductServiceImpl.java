package com.example.springboot.service.impl;

import com.example.springboot.data.dao.ProductDAO;
import com.example.springboot.data.dto.ProductDto;
import com.example.springboot.data.dto.ProductResponseDto;
import com.example.springboot.data.entity.Product;
import com.example.springboot.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number){
        logger.info("Get product by number: {}", number);
        Product product = productDAO.selectProduct(number);
        logger.info("Get product by number: {}", product);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());
        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto){
        logger.info("Save product: {}", productDto);
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);
        logger.info("Save product: {}", savedProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());
        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        logger.info("Change product name: {}", name);
        Product changedProduct = productDAO.updateProductName(number, name);
        logger.info("Change product name: {}", changedProduct);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(name);
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());
        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        logger.info("Delete product by number: {}", number);
        productDAO.deleteProduct(number);
    }

}
