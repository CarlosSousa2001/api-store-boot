package com.crs.datajpa.controller;

import com.crs.datajpa.model.Category;

import com.crs.datajpa.model.dto.CategoryDTO;
import com.crs.datajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        var savedCategory = categoryService.createCategoryService(categoryDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        var getCategory = categoryService.getCategoryByIdService(id);

        return ResponseEntity.ok().body(getCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getListCategory(){
        List<CategoryDTO> categoryDTOList = categoryService.getCategoryList();

        return ResponseEntity.ok().body(categoryDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        var updateCategory = categoryService.updateCategoryService(id, categoryDTO);

        return ResponseEntity.ok().body(updateCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryByIdService(id);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria deletada");
    }
}
























