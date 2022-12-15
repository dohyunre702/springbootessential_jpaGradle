package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

//ProductService 인터페이스의 구현체
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    @Autowired //dto 객체를 생성하고 값을 넣어 초기화 > builder패턴 활용 가능
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override //조회하기
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    //저장하기: 전달받은 DTO객체를 통해 엔티티 객체 생성해 초기화한 후, DAO 객체로 전달
    //상품 정보ㅡㄹ 전달하고 애플리케이션을 거쳐 DB에 저장하는 역할 수행
    public ProductResponseDto saveProduct(ProductDto productDto) {
        //엔티티 객체 생성해 초기화한다.
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        //DAO객체로 전달한다.
        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    };

    @Override //업데이트 - 상품정보 중 이름을 변경
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        //클라이언트로부터 대상을 식별할 수 있는 인덱스 값과 변경하려는 이름을 받아온다.
        //실제 변경 작업은 dao에서 진행 > 서비스 레이어에서는 메서드를 호출한 결과값만 받아 옴.
        Product changedProduct = productDAO.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    };

    @Override //삭제
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);

    };

}
