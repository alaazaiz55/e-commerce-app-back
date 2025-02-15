package com.MyProject.Ecom.service.customer;

import com.MyProject.Ecom.dto.OrderedProductsResponseDto;
import com.MyProject.Ecom.dto.ReviewDto;

public interface ReviewService {

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
    ReviewDto giveReview(ReviewDto reviewDto) throws Exception;
}
