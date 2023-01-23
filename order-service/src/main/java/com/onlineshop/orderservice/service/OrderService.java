package com.onlineshop.orderservice.service;

import com.onlineshop.orderservice.dto.OrderRequest;
import org.springframework.stereotype.Service;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
