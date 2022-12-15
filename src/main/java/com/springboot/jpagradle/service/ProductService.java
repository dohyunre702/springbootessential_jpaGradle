package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;

//crud 기능 호출을 위한 메서드 정의
//역할: dao에서 구현한 기능을 서비스 인터페이스에서 호출에 결괏값을 가져온다. 리턴 타입은 DTO 객체이다.
//=서비스 레이어에서 DTO 객체와 엔티티 객체를 각 레이어에 변환해 전달하는 역할을 한다.
public interface ProductService {
    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
