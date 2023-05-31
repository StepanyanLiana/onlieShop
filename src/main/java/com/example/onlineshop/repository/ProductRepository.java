package com.example.onlineshop.repository;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getProductsByCategory(Category category);
}
