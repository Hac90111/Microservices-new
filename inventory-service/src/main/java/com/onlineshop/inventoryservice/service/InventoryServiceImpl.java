package com.onlineshop.inventoryservice.service;

import com.onlineshop.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository repository;


    @Override
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return repository.findBySkuCode(skuCode).isPresent();
    }
}
