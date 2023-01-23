package com.onlineshop.productservice.service.impl;

import com.onlineshop.productservice.dto.ProductResponse;
import com.onlineshop.productservice.dto.ProductRequest;
import com.onlineshop.productservice.model.Product;
import com.onlineshop.productservice.repository.ProductRepository;
import com.onlineshop.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  // it will create constructor for us of ProductRepository interface
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;


    @Override
    public void createProduct(ProductRequest productRequest) {
        //map ProductRequest into Product entity
        Product product= Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getName());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> response = products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
        return response;
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
