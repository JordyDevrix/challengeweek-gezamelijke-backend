package com.example.webshop.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "order_products")
public class OrderProduct {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JsonBackReference
    private Order order_id;

    @ManyToOne
    private Webshop webshop;

    private String product_id;

    private String prefixId;

    private double price;


    public OrderProduct(Webshop webshop, String productId, String prefixId, double price) {
        this.webshop = webshop;
        this.product_id = productId;
        this.prefixId = prefixId;
        this.price = price;

    }

    public OrderProduct() {

    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }

    public Webshop getWebshop() {
        return webshop;
    }

    public void setWebshop(Webshop webshop) {
        this.webshop = webshop;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPrefixId() {
        return prefixId;
    }

    public void setPrefixId(String prefixId) {
        this.prefixId = prefixId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
