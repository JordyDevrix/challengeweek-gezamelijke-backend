package com.example.webshop.dao;

import com.example.webshop.models.CustomUser;
import com.example.webshop.models.Order;
import com.example.webshop.models.Product;
import com.example.webshop.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDAO {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;

    public OrderDAO(OrderRepository orderRepository, UserRepository userRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public void createOrder(CustomUser user, List<Product> products, String status) {


        if (!products.isEmpty()) {
            Date date = new Date();
            String orderDate = date.toString();
            Double totalPrice = calculateTotalPrice(products);

            Order order = new Order(user, products, orderDate, status, totalPrice);
            this.orderRepository.save(order);
            return;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Products not found"
        );
    }

    @Transactional
    public void payOpenOrder(CustomUser user, long orderId, String status) {
        Optional<Order> order = this.orderRepository.findById(orderId);
        if (order.isPresent()) {
            Order orderToPay = order.get();
            if (orderToPay.getUser().getId() == user.getId() && (orderToPay.getStatus().equals("pending") || orderToPay.getStatus().equals("instalment1") || orderToPay.getStatus().equals("instalment2") || orderToPay.getStatus().equals("instalment3") || orderToPay.getStatus().equals("afterpay"))) {
                orderToPay.setStatus(status);
                this.orderRepository.save(orderToPay);
                return;
            }
            throw new ResponseStatusException(
                    HttpStatus.NOT_MODIFIED, "Payment not possible"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Order not found"
        );
    }

    public double calculateTotalPrice(List<Product> products) {
        double total = 0.0;
        for (Product product : products) {
            double price = product.getPrice();
            total += price;
        }
        return total;
    }

    public List<Order> getAllOrdersByUser(long id) {
        List<Order> orders = this.orderService.getAllOrders(id);
        if (orders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No orders found with that user id"
            );
        }
        return orders;
    }

}
