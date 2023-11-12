package com.example.ecommerce.controllers;

import com.example.ecommerce.model.ProductModel;
import com.example.ecommerce.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    private List<ProductModel> getProducts(@RequestParam("query") @Nullable String query){
        System.out.println("Products listing");
        return productService.getProducts(query);
    }

    @PostMapping("/")
    private ProductModel createProduct(@RequestBody ProductModel productData){
        return productService.createProduct(productData);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProductModel> getProduct(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductModel productDeta){
        return productService.updateProduct(id, productDeta);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
