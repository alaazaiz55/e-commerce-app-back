package com.MyProject.Ecom.entity;


import jakarta.persistence.Entity;
import lombok.Data;


@Data

public class CartItemsDto {

    private Long id;

    private Long price;
     private Long quantity;
     private Long productId;

     private Long orderId;
     private String productName;
     private byte[] returnedImg;
     private Long userId;
}
