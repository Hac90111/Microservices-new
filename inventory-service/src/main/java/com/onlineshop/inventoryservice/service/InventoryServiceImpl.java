package com.onlineshop.inventoryservice.service;

import com.onlineshop.inventoryservice.dto.InventoryResponse;
import com.onlineshop.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return repository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity()>0)
                                .build()).collect(toList());
    }
}
