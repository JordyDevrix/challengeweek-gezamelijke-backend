package com.example.webshop.dto;

import com.example.webshop.models.CustomUser;
import com.example.webshop.models.Product;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Id;

import java.util.List;

public class OrderDTO {

    @Id
    @JsonAlias("id")
    private Long id;

    @JsonAlias("user_id")
    private CustomUser user;
    @JsonAlias("products")
    private List<Product> products;
    private String orderDate;
    private String status;
    private double totalPrice;

    public OrderDTO(Long id, CustomUser user, List<Product> products, String orderDate, String status) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductId() {
        return products.get(0).getId();
    }
}
