package com.example.onlineshop.service;
import com.example.onlineshop.entity.Order;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.security.CurrentUser;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrder();

    void remove(int id);

    void save(Order order);

    boolean save(CurrentUser currentUser, Product productId);
}