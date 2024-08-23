package com.MyProject.Ecom.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ProductDto {

    private Long id;

    private String name;

    private Long price;

    private String description;

    private byte[] byteImg;

    private Long categoryId;
    private String categoryName;



    private MultipartFile img;
    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }



}
