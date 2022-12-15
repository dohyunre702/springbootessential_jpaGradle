package com.springboot.jpagradle.data.repository;

//데이터베이스 저장 테스트

import com.springboot.jpagradle.entity.Product;
import com.springboot.jpagradle.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save() {
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.save(product);

        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }
}
