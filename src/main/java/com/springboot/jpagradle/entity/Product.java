package com.springboot.jpagradle.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "name")
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

}
