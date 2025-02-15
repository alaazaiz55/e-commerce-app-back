package com.MyProject.Ecom.controller.customer;

import com.MyProject.Ecom.dto.OrderedProductsResponseDto;
import com.MyProject.Ecom.dto.ReviewDto;
import com.MyProject.Ecom.service.customer.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId){

        return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));

    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws Exception{
        ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);
        if(reviewDto1 == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Somthing Worng");
            return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);


    }
}
