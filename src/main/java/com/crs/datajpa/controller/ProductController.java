package com.crs.datajpa.controller;

import com.crs.datajpa.model.Product;
import com.crs.datajpa.model.dto.ProductDTO;
import com.crs.datajpa.model.dto.ProductResponseDTO;
import com.crs.datajpa.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        var productRes = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getUserById(@Valid @PathVariable Long id) throws Exception {
        var getProductRes = productService.getById(id);
        return  ResponseEntity.ok().body(getProductRes);
    }
}
