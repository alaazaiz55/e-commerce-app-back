package com.MyProject.Ecom.service.customer;

import com.MyProject.Ecom.dto.ProductDetailDto;
import com.MyProject.Ecom.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> getAllProduct();
    List<ProductDto> getAllProductByName(String name);
    ProductDetailDto getProductDetailsById(Long productId);

}
