package com.MyProject.Ecom.dto;


import com.MyProject.Ecom.entity.FAQDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailDto {

    private ProductDto productDto;

    private List<FAQDto> faqDtoList;
}
