package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//JPA리포지토리 = Spring Data Jpa가 제공하는 인터페이스
//ProductRepository가 JPA리포지토리를 상속받을 때는 대상 엔티티와 기본값 타입을 지정해야 한다.
public interface ProductRepository extends JpaRepository<Product, Long> {
    //대상 엔티티: Product
    //기본값 타입 = @Id 타입 = Long
}
