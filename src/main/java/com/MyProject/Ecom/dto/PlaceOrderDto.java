package com.MyProject.Ecom.dto;


import lombok.Data;

@Data
public class PlaceOrderDto {

    private Long id;
    private String address;
    private String orderDescription;
}
