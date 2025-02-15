package com.MyProject.Ecom.controller.customer;

import com.MyProject.Ecom.dto.AddProductInCartDto;
import com.MyProject.Ecom.dto.PlaceOrderDto;
import com.MyProject.Ecom.dto.ProductDto;
import com.MyProject.Ecom.entity.OrderDto;
import com.MyProject.Ecom.exceptions.ValidationException;
import com.MyProject.Ecom.service.customer.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart/{id}")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto,  @PathVariable String id){

        try {
            Long validId = Long.valueOf(id);

            return cartService.addProductToCart(addProductInCartDto, validId);

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        }


       // return cartService.addProductToCart(addProductInCartDto, id);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long id){

        OrderDto orderDto = cartService.getCartByUserId(id);
        return  ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }


    @GetMapping("/coupon/{id}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long id , @PathVariable String code){
        try {
            OrderDto orderDto = cartService.applyCoupon(id, code);
            return ResponseEntity.ok(orderDto);
        } catch (ValidationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/addition/{id}")
    public ResponseEntity<OrderDto> increaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto,  @PathVariable String id){
        Long validId = Long.valueOf(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDto, validId));
    }

    @PostMapping("/deduction/{id}")
    public ResponseEntity<OrderDto> decreaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto,  @PathVariable String id){
        Long validId = Long.valueOf(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDto, validId));
    }

    @PostMapping("/placeOrder/{id}")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto, @PathVariable String id){
        Long validId = Long.valueOf(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder( placeOrderDto, validId));
    }

    @GetMapping("/myOrders/{id}")
    public ResponseEntity<List<OrderDto>> getMyplaceOrders(@PathVariable Long id){
        Long validId = Long.valueOf(id);

        return ResponseEntity.ok(cartService.getMyPlacedOrder(validId));
    }

    }
