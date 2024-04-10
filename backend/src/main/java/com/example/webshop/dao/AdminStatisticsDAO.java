package com.example.webshop.dao;

import com.example.webshop.models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class AdminStatisticsDAO {

    private OrderRepository orderRepository;

    public AdminStatisticsDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public double getTotalSalesPrice() {
        List<Order> allOrders = orderRepository.findAll();
        if (!allOrders.isEmpty()) {
            double salesAmount = 0;
            for (Order order : allOrders) {
                salesAmount += order.getTotalPrice();
            }
            return salesAmount;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No sales made so far"
            );
        }
    }

    public int getTotalSalesProducts() {
        List<Order> allOrders = orderRepository.findAll();
        if (!allOrders.isEmpty()) {
            return allOrders.size();
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No sales made so far"
            );
        }
    }

    public double getTotalSalesPriceYear(String year) {
        // Moet nog aangepast worden, kan pas na Orders fix
        List<Order> allOrders = orderRepository.findAll();
        if (!allOrders.isEmpty()) {
            double salesAmount = 0;
            for (Order order : allOrders) {
                salesAmount += order.getTotalPrice();
            }
            return salesAmount;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No sales made so far"
            );
        }
    }
}
