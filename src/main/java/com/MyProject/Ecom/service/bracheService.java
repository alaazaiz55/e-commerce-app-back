package com.MyProject.Ecom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class bracheService {

    private final CouponRepository couponRepository;

    public Coupon creatCoupon(Coupon coupon){

        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("coupon code already exists.");
        }
        return couponRepository.save(coupon);
        System.out.println("test");
        System.out.println("test1");
        System.out.println("test2");
        System.out.println("test3");
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }

}