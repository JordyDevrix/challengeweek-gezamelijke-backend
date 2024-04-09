package com.example.webshop.services;

import com.example.webshop.models.Order;
import com.example.webshop.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders(long userId) {
        return this.orderRepository.findAll().stream()
                .filter(order -> order.getUser().getId() == userId)
                .collect(Collectors.toList());
    }
}