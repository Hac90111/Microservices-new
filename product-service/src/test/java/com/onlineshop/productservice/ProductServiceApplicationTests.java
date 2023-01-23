package com.onlineshop.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshop.productservice.dto.ProductRequest;
import com.onlineshop.productservice.dto.ProductResponse;
import com.onlineshop.productservice.model.Product;
import com.onlineshop.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Integration Testing using containers
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc  // to have to mockMvc Dependency
class ProductServiceApplicationTests {
    @Container
    static MongoDBContainer mongoDBContainer= new MongoDBContainer("mongo:latest");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;  // convert POJO into JSON and vice-versa(import com.fasterxml.jackson.databind.ObjectMapper)

    @Autowired
    private ProductRepository repository;


    @DynamicPropertySource // to add the properties at runtime
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestString))
                .andExpect(status().isCreated());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        Product product = getProduct();
        repository.save(product);
       mockMvc.perform(MockMvcRequestBuilders.get("/api/product"))
               .andDo(print()).andExpect(status().isOk());
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Iphone13")
                .description("I-Phone13 released")
                .price(BigDecimal.valueOf(1300))
                .build();

    }

    private Product getProduct() {
        return Product.builder()
                .name("Iphone13")
                .description("I-Phone13 released")
                .price(BigDecimal.valueOf(1300))
                .build();

    }

}
