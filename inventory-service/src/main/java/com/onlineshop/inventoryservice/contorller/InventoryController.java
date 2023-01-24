package com.onlineshop.inventoryservice.contorller;

import com.onlineshop.inventoryservice.dto.InventoryResponse;
import com.onlineshop.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService service;

    //http://localhost:4040/api/inventory/iphone-13,iphone13-red  by @PathVariable
    //http://localhost:4040/api/inventory?sku-code=iphone-13&sku-code=iphone13-red   by @RequestParam

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return service.isInStock(skuCode);
    }
}
