package com.example.ecommerce.services;

import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.model.ProductModel;
import com.example.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> getProducts(String query){
        List<ProductModel> products = productRepository.findAll();
        if(query != null && !query.isEmpty()){
            String parsedQuery = query.toLowerCase();
            products = products.stream().filter(item ->
                    item.getName().toLowerCase().contains(parsedQuery) || item.getDescription().toLowerCase().contains(parsedQuery))
                    .collect(Collectors.toList());
        }
        return products;
    }

    private ProductModel queryProductById(Long id){
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("No Product matching %s", id)));
    }

    public ProductModel createProduct(ProductModel productModel){
        return productRepository.save(productModel);
    }

    public ResponseEntity<ProductModel> getProductById(Long id){
        ProductModel product = queryProductById(id);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<ProductModel> updateProduct(Long id, ProductModel productModel){
        ProductModel product = queryProductById(id);
        product.setName(productModel.getName());
        product.setDescription(product.getDescription());

        ProductModel updatedProduct = productRepository.save(product);

        return ResponseEntity.ok(updatedProduct);
    }

    public ResponseEntity<Map<String, String>> deleteProduct(Long id){
        ProductModel product = queryProductById(id);
        productRepository.delete(product);
        HashMap<String, String> response = new HashMap<>();
        response.put("detail", "Deleted");
        return ResponseEntity.ok(response);
    }
}
