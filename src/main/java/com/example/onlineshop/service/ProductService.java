package com.example.onlineshop.service;

import com.example.onlineshop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product, MultipartFile multipartFile, int categoryId) throws IOException;
}
