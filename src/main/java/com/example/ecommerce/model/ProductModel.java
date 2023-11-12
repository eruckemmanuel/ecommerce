package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.ecommerce.utils.HashMapConverter;

import java.util.Map;

@Entity
@Getter
@Setter
@Table(name="products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Float price;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> metadata;
}
