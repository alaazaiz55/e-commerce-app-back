package com.MyProject.Ecom.controller.customer;


import com.MyProject.Ecom.dto.ProductDetailDto;
import com.MyProject.Ecom.dto.ProductDto;
import com.MyProject.Ecom.service.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = customerProductService.getAllProduct();
        return  ResponseEntity.ok(productDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name){
        List<ProductDto> productDtos = customerProductService.getAllProductByName(name);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailDto> getProductDetailsById(@PathVariable Long productId){
        ProductDetailDto productDetailDto = customerProductService.getProductDetailsById(productId);
        if(productDetailDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailDto);
    }
}
