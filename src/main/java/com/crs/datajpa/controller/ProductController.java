package com.crs.datajpa.controller;

import com.crs.datajpa.model.Product;
import com.crs.datajpa.model.User;
import com.crs.datajpa.service.ProductService;
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
    public ResponseEntity<Product> createProduct(@RequestBody Product productRequest) {
        var productRes = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getUserById(@PathVariable Long id) throws Exception {
        var getProductRes = productService.getById(id);
        return  ResponseEntity.ok().body(getProductRes);
    }
}
