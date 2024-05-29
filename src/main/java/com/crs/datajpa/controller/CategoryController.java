package com.crs.datajpa.controller;

import com.crs.datajpa.model.Category;

import com.crs.datajpa.model.dto.CategoryDTO;
import com.crs.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        var savedCategory = categoryService.createCategoryService(categoryDTO);

        return  ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
}
