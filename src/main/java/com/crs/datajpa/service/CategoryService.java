package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.Category;
import com.crs.datajpa.model.dto.CategoryDTO;
import com.crs.datajpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategoryService(CategoryDTO categoryDTO) {

        Category category = new Category();

        category.setNameCategory(categoryDTO.nameCategory());

        var savedCategory  = categoryRepository.save(category);

        return  new CategoryDTO(savedCategory.getId(), savedCategory.getNameCategory());
    }

    @Transactional(readOnly = true)
    public CategoryDTO getCategoryByIdService(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if(categoryOptional.isEmpty()){
            throw new EntityNotFoundException();
        }
        return new CategoryDTO(categoryOptional.get().getId(), categoryOptional.get().getNameCategory());
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategoryList(){
        List<Category> categoryList = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for(Category item : categoryList){
            CategoryDTO categoryDTO = new CategoryDTO(item.getId() ,item.getNameCategory());
            categoryDTOList.add(categoryDTO);
        }

        return categoryDTOList;
    }

    public CategoryDTO updateCategoryService(Long id, CategoryDTO categoryDTO){

        Category categoryOptional = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        categoryOptional.setNameCategory(categoryDTO.nameCategory());

        var savedCategory = categoryRepository.save(categoryOptional);

        return  new CategoryDTO(savedCategory.getId(), savedCategory.getNameCategory());
    }


    public void deleteCategoryByIdService(Long id){
        categoryRepository.deleteById(id);
    }
}




















