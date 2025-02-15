package com.MyProject.Ecom.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {

    private Long id;

    private String orderDescription;

    private Date date ;

    private Long amount;

    private String adress;

    private String payment;



    private OrderStatus orderStatus;

    private Long totalAmount;
    private Long discount;
    private UUID trackingId;

    private String userName;


    private List<CartItemsDto> cartItems;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    private String couponName;
}
