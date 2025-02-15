package com.MyProject.Ecom.service.customer;

import com.MyProject.Ecom.dto.AddProductInCartDto;
import com.MyProject.Ecom.dto.PlaceOrderDto;
import com.MyProject.Ecom.entity.OrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto, Long id);
    OrderDto getCartByUserId(Long id);
    OrderDto applyCoupon (Long id , String code);
    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto, Long id);
    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto, Long id);
    OrderDto placeOrder(PlaceOrderDto placeOrderDto, Long id);
    List<OrderDto> getMyPlacedOrder(Long userId);
}
