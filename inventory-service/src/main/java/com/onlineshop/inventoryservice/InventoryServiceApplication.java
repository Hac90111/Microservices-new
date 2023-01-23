package com.onlineshop.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository repository){
//		return args -> {
//			Inventory inventory1= new Inventory();
//			inventory1.setSkuCode("iphone_13");
//			inventory1.setQuantity(100);
//			Inventory inventory2= new Inventory();
//			inventory2.setSkuCode("iphone_13_red");
//			inventory2.setQuantity(0);
//			repository.save(inventory1);
//			repository.save(inventory2);
////		};
//	}
}