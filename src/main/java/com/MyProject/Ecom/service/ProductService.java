package com.MyProject.Ecom.service;

import com.MyProject.Ecom.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto addProduct (ProductDto productDto) throws Exception;
    List<ProductDto> getAllProduct();
    List<ProductDto> getAllProductByName(String name);
    boolean deleteProduct(Long id);
}
