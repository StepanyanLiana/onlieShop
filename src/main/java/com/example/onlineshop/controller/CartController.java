package com.example.onlineshop.controller;


import com.example.onlineshop.security.CurrentUser;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @PostMapping
    public String addProductToCart(@RequestParam("id") int productId,
                                   @AuthenticationPrincipal CurrentUser currentUser) {
        cartService.save(currentUser, productId);
        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(ModelMap model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("cart", cartService.findByUser(currentUser));
        model.addAttribute("products", productService.findAll());
        return "cart";
    }
}