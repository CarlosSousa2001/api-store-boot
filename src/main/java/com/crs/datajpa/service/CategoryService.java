package com.crs.datajpa.service;

import com.crs.datajpa.model.Category;
import com.crs.datajpa.model.dto.CategoryDTO;
import com.crs.datajpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategoryService(CategoryDTO categoryDTO) {

        Category category = new Category();

        category.setNameCategory(categoryDTO.nameCategory());

        var savedCategory  = categoryRepository.save(category);

        return  new CategoryDTO(savedCategory.getNameCategory());
    }

    public CategoryDTO getCategoryByIdService(CategoryDTO categoryDTO) {

    }
}
