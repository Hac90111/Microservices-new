package com.onlineshop.inventoryservice.service;

import com.onlineshop.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> skuCode);
}
