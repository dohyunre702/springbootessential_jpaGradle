package com.springboot.jpagradle.service.impl;

import com.springboot.jpagradle.data.dto.ProductDto;
import com.springboot.jpagradle.data.dto.ProductResponseDto;
import com.springboot.jpagradle.entity.Product;
import com.springboot.jpagradle.repository.ProductRepository;
import com.springboot.jpagradle.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

//@MockBean 어노테이션을 사용한 테스트 환경 설정 => Mock 객체 생성 후 스프링에서 의존성 주입을 받음.
@ExtendWith(SpringExtension.class)
@Import({ProductServiceImpl.class})
class ProductServiceTest2 {
    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    void getProductTest() { //getProduct 메서드 단위 테스트. given-when-then
        //given: 테스트에 사용될 Product 엔티티 객체 생성하고 ProductRepository 동작에 대한 결괏값 리턴 설정
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        //when. getProduct 메소드 호출해 동작 테스트
        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        //값 검증
        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        //검증 보완 (부가 검증)
        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest() { //saveProduct 메서드 단위 테스트
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(new ProductDto("펜", 1000, 1234));

        Assertions.assertEquals(productResponseDto.getName(), "펜");
        Assertions.assertEquals(productResponseDto.getPrice(), 1000);
        Assertions.assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }

}
