package com.MyProject.Ecom.controller.admin;


import com.MyProject.Ecom.entity.OrderDto;
import com.MyProject.Ecom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminOrderController {

    private final OrderService orderService;



    @GetMapping("/placeOrder")
    public ResponseEntity<List<OrderDto>> getAllPlacedOrder(){
        return ResponseEntity.ok(orderService.getAllPlaceOrder());
    }



    @GetMapping("/order/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status){
        OrderDto orderDto = orderService.changeOrderStatus(orderId, status);
        if(orderId == null)
            return new ResponseEntity<>("Somthing went worng" , HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.OK).body(orderDto);

    }
}
