package com.example.onlineshop.service;

import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.security.CurrentUser;

import java.util.List;

public interface CartService {
    void save(Cart cart, int productId, CurrentUser currentUser);

    List<Cart> findAll();

    void save(CurrentUser currentUser, int productId);

    Cart findByUser(CurrentUser currentUser);
}
