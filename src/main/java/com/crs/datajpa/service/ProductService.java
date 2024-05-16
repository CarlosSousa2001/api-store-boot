package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.ProductNotFoundException;
import com.crs.datajpa.model.Product;
import com.crs.datajpa.model.User;
import com.crs.datajpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product productRequest) {
        return productRepository.save(productRequest);
    }

    public Product getById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException(id);
        };

        return productOptional.get();
    }
}
