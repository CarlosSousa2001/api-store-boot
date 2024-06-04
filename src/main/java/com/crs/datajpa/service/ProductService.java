package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.Category;
import com.crs.datajpa.model.Product;
import com.crs.datajpa.model.dto.CategoryDTO;
import com.crs.datajpa.model.dto.ProductDTO;
import com.crs.datajpa.model.dto.ProductResponseDTO;
import com.crs.datajpa.repository.CategoryRepository;
import com.crs.datajpa.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductResponseDTO createProduct(ProductDTO productDTO) {

        // preciso pegar o id que vem dentro da record List<LONG> ali tem os IDS das categorias

        // instanciar objeto real do category
        List<Category> categoryList = new ArrayList<>();

        // pego minha lista de ids do meu record
        List<Long> categoryIds = productDTO.categoryIds();

        for(Long item: categoryIds){
            Category categoryExist = categoryRepository.findById(item).orElseThrow(
                    () -> new EntityNotFoundException()
            );
            categoryList.add(categoryExist);
        }


        Product createProduct = new Product();

        BeanUtils.copyProperties(productDTO, createProduct, "categoryIds");

        createProduct.getCategories().addAll(categoryList);

        var savedProduct = productRepository.save(createProduct);

        List<CategoryDTO> categoryDTOs = categoryList.stream()
                .map(category -> new CategoryDTO(category.getId(), category.getNameCategory()))
                .collect(Collectors.toList());


        return new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getTitle(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getCod(),
                savedProduct.getPhotoUrl(),
                categoryDTOs,
                savedProduct.getTags()
        );
    }

    public Product getById(Long id) {
       return productRepository.findById(id).orElseThrow(
               () -> new EntityNotFoundException()
       );
    }
}
