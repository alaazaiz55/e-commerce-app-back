package com.MyProject.Ecom.service;

import com.MyProject.Ecom.entity.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllPlaceOrder();
    OrderDto changeOrderStatus(Long orderId, String status);
}
