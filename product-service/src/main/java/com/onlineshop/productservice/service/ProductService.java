package com.onlineshop.productservice.service;

import com.onlineshop.productservice.dto.ProductResponse;
import com.onlineshop.productservice.dto.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
