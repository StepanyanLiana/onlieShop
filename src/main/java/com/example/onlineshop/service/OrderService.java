package com.example.onlineshop.service;
import com.example.onlineshop.entity.Order;
import java.util.List;

public interface OrderService {

    List<Order> findAllOrder();

    void remove(int id);

    void save(Order order);
}