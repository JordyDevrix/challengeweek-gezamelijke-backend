package com.example.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @JsonInclude
    private long id;

    @ManyToOne
    @JsonBackReference
    @JsonInclude
    private CustomUser user;

    @OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL)
    @JsonInclude
    private List<OrderProduct> products;

    private String orderDate;
    private String status;
    private double totalPrice;

    public Order(CustomUser user, String orderDate, String status, double totalPrice) {
        this.user = user;
        this.products = products;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order() {

    }

    public long getId() {
        return id;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
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
}
