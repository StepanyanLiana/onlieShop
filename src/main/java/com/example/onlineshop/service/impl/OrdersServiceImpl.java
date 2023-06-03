package com.example.onlineshop.service.impl;

import com.example.onlineshop.entity.Order;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.security.CurrentUser;
import com.example.onlineshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void remove(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public boolean save(CurrentUser currentUser, Product productId) {
        Order order = new Order();
        order.setUser(currentUser.getUser());
        order.addProduct(productId);
        orderRepository.save(order);
        return false;
    }

}
