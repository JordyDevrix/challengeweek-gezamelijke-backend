package com.example.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


public class Product {
    private String id;
    private String name;
    private String description;
    private int stock = 0;
    private double price = 0.0;
    private String imageUrl;
    private String brand;
    private String instruction;
    private String terms;


    private String category;

    public Product(String name, String description, int stock, double price, String imageUrl, String brand, String instruction, String terms, String category) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.imageUrl = imageUrl;
        this.brand = brand;
        this.instruction = instruction;
        this.terms = terms;
        this.category = category;
    }

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdPrefix(String prefix, long id) {
        this.id = prefix + id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
