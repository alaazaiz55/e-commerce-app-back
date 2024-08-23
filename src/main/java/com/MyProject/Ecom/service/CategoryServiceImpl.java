package com.MyProject.Ecom.service;


import com.MyProject.Ecom.dto.CategoryDto;
import com.MyProject.Ecom.entity.Category;
import com.MyProject.Ecom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createcategory(CategoryDto categoryDto){

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryRepository.save(category);
    }


    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
}
