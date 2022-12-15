package com.springboot.jpagradle.service.impl;

import com.springboot.jpagradle.data.dto.ProductDto;
import com.springboot.jpagradle.data.dto.ProductResponseDto;
import com.springboot.jpagradle.entity.Product;
import com.springboot.jpagradle.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

//외부 요인 배제한 단위 테스트 코드 작성
public class ProductServiceTest {

    //Mockito.mock()메서드로 ProductRepository 주입 => 리포지토리를 Mock 객체로 대체 (스프링빈 사용 x)
    private ProductRepository productRepository =
            Mockito.mock(ProductRepository.class);

    //서비스 객체 직접 초기화
    private ProductServiceImpl productService;

    @BeforeEach //각 테스트 전에 ProductService 객체를 초기화해 사용
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

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
