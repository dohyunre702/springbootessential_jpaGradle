package com.springboot.jpa.data.dto;

public class ChangeProductNameDto {
    private Long number;
    private String name;


    //생성자
    public ChangeProductNameDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }

    public ChangeProductNameDto() {}

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
}
