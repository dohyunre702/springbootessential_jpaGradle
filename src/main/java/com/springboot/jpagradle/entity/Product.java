package com.springboot.jpa.data.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//application.properties의 spring.jpa.hibernate.ddl-auto=create에 의해 쿼리문 없이도 db에 테이블 자동 생성
@Entity
@Table(name="product")
public class Product {

    @Id //테이블의 기본값
    @GeneratedValue(strategy = GenerationType.IDENTITY) //해당 필드의 값을 어떤 방식으로 자동으로 생성할지 결정
    private Long number;

    @Column(nullable = false) //컬럼. 필드에 몇 가지 설정 더할 때 사용
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //getter and setter
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
