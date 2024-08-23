package com.MyProject.Ecom.controller.admin;


import com.MyProject.Ecom.dto.ProductDto;
import com.MyProject.Ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @PostMapping("/product")

    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws Exception {
        ProductDto productDto1 = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = productService.getAllProduct();
        return  ResponseEntity.ok(productDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name){
        List<ProductDto> productDtos = productService.getAllProductByName(name);
        return ResponseEntity.ok(productDtos);
    }

    @DeleteMapping("/product/{productId}")
    public  ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        boolean deleted = productService.deleteProduct(productId);
        if(deleted){
            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.notFound().build();
    }

}
