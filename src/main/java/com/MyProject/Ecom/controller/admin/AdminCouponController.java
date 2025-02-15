package com.MyProject.Ecom.controller.admin;


import com.MyProject.Ecom.entity.Coupon;
import com.MyProject.Ecom.exceptions.ValidationException;
import com.MyProject.Ecom.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminCouponController {

private final CouponService couponService;

    @PostMapping("/coupons")
    public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon){
        try {
            Coupon createdCoupon = couponService.creatCoupon(coupon);
            return ResponseEntity.ok(createdCoupon);
        } catch (ValidationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }


    }
    @GetMapping("/coupons")
    public ResponseEntity<List<Coupon>> getAllCoupons(){
       return ResponseEntity.ok(couponService.getAllCoupons());

    }


}
