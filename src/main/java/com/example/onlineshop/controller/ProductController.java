package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Product;
import com.example.onlineshop.repository.CategoryRepository;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryRepository categoryRepository;



    @GetMapping()
    public String productPage(ModelMap modelMap) {
        modelMap.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/add")
    public String productAddPage(ModelMap modelMap) {
        List<Product> products = productService.findAll();
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "addproduct";
    }

    @PostMapping("/add")
    public String productAdd(@ModelAttribute Product product, @RequestParam("image") MultipartFile multipartFile, @RequestParam("category_id") int categoryId) throws IOException {
        productService.save(product,multipartFile,categoryId);
        return "redirect:/product";
    }
}