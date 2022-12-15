package com.springboot.jpagradle.data.repository;

import com.springboot.jpagradle.entity.Product;
import com.springboot.jpagradle.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/* @SpringBootTest 어노테이션을 활용한 기본 메서드 테스트
 - 스프링의 모든 설정을 가져오고, 빈 객체도 전체 스캔 > 의존성 주입 고민 x
 - 테스트 속도가 느려 권장하지 않음
 */
@SpringBootTest
public class ProductRepositoryTest2 {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Test
    public void basicCRUDTest() {
        /* create */
        //given 한 번만 사용
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();
        
        //when
        Product savedProduct = productRepository.save(givenProduct);
        
        //then
        Assertions.assertThat(savedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(savedProduct.getName())
                .isEqualTo(givenProduct.getName());
        Assertions.assertThat(savedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(savedProduct.getStock())
                .isEqualTo(givenProduct.getStock());
        
        /* read */
        //when
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);
        
        //then
        Assertions.assertThat(selectedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(selectedProduct.getName())
                .isEqualTo(givenProduct.getName());
        Assertions.assertThat(selectedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(selectedProduct.getStock())
                .isEqualTo(givenProduct.getStock());
        
        /* update */
        //when
        Product foundProduct = productRepository.findById(selectedProduct.getNumber())
                .orElseThrow(RuntimeException::new);
        
        foundProduct.setName("장난감");
        
        Product updatedProduct = productRepository.save(foundProduct);
        
        //then
        assertEquals(updatedProduct.getName(), "장난감");
        
        /* delete */
        //when
        productRepository.delete(updatedProduct);
        
        //then
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());
        
    }

}
