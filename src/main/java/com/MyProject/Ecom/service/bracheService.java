package com.MyProject.Ecom.service;

import com.MyProject.Ecom.entity.Coupon;
import com.MyProject.Ecom.exceptions.ValidationException;
import com.MyProject.Ecom.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class bracheService {

    private final CouponRepository couponRepository;

    public Coupon creatCoupon(Coupon coupon){

        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("coupon code already exists.");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }

}
