package com.MyProject.Ecom.controller.admin;


import com.MyProject.Ecom.dto.CategoryDto;
import com.MyProject.Ecom.entity.Category;
import com.MyProject.Ecom.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<Category> creatCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.createcategory(categoryDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(category);



    }
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory(){

        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}
