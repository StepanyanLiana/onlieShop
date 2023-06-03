package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;
    @GetMapping()
    public String categoryPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "category";
    }
    @GetMapping("/{id}")
    public String singleCategoryPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Category> byId = categoryService.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            modelMap.addAttribute("categories", category);
            modelMap.addAttribute("products", productRepository.getProductsByCategory(category));
            return "singleCategory";
        } else {
            return "redirect:/categories";
        }
    }
    @GetMapping("/remove")
    public String removePatient(@RequestParam("id") int id){
        productRepository.deleteById(id);
        return "redirect:/category";
    }
}