package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Order;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.security.CurrentUser;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.OrderService;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping
    public String orderPage(ModelMap modelMap) {
        modelMap.addAttribute("orders", orderService.findAllOrder());
        return "order";
    }

    @GetMapping("/remove")
    public String removeOrder(@RequestParam("title") int id) {
        orderService.remove(id);
        return "redirect:/order";
    }

    @PostMapping
    public String orderProduct(@RequestParam("id") Product productId,
                               @AuthenticationPrincipal CurrentUser currentUser) {
        if ( orderService.save(currentUser, productId)) {
            cartService.delete(productId);
        }
        return "order";
    }
}
