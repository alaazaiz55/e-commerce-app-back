package com.MyProject.Ecom.service;

import com.MyProject.Ecom.dto.CategoryDto;
import com.MyProject.Ecom.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createcategory(CategoryDto categoryDto);
    List<Category> getAllCategory();
}
