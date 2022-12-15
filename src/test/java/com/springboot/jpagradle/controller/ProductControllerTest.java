package com.springboot.jpagradle.controller;

import com.google.gson.Gson;
import com.springboot.jpagradle.data.dto.ProductDto;
import com.springboot.jpagradle.data.dto.ProductResponseDto;
import com.springboot.jpagradle.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductControllerTest.class) //테스트 대상 클래스만 로드 (@SpringBootTest보다 가볍게 테스트)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception { //getProduct메서드 테스트

        //Given: 어떤 메서드가 호출되고 어떤 파라미터를 주입받는지 가정함
        //willReturn() 어떤 결과를 리턴할 것인지 정의
        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(123L, "pen", 5000, 2000));
        String productId = "123";

        mockMvc.perform(//MockMvcRequestBuilders에서 제공하는 HTTP메서드
                    get("/product?number=" + productId)) //URL 정의해 사용
                //결과값으로 ResultActions 객체가 리턴. andExpect()로 결괏값 검증 수행
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .adDo(print()); //요청과 응답의 전체 내용 확인

        //지정된 메서드가 실행되었는지 검증 - given()에서 정의된 동작과 대응
        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 생성 테스트")
    void createProductTest() throws Exception {//createProduct메서드 테스트
        given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
                .willReturn(new ProductResponseDto(12315L, "pen", 5000, 2000));

        ProductDto productDto = ProductDto.builder() //빌더 패턴을 롬복으로(Dto참고)
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(
                post("/product")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print()); //요청과 응답의 전체 내용 확인

        verify(productService).saveProduct(new ProductDto("pen", 5000, 2000));
    }
}
