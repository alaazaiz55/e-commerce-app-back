package com.MyProject.Ecom.service;

import com.MyProject.Ecom.entity.Coupon;

import java.util.List;

public interface CouponService {

    Coupon creatCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
}
