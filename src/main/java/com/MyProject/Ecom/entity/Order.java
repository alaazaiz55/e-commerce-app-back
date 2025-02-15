package com.MyProject.Ecom.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "orders")
@Data

@Entity

@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id" , referencedColumnName = "id" )
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id" , referencedColumnName = "id" )
    private Coupon coupon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItems> cartItems;

   public OrderDto getOrderDto(){

        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setOrderDescription(orderDescription);
        orderDto.setAdress(adress);
        orderDto.setTrackingId(trackingId);
        orderDto.setAmount(amount);
        orderDto.setDate(date);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setUserName(user.getFirstname());
        if (coupon != null){
            orderDto.setCouponName(coupon.getName());
        }
        return orderDto;

    }
}
