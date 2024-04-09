package com.example.webshop.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ProductDTO {

    public String name;
    public String description;
    public int stock = 0;
    public double price = 0.0;
    public String imageUrl;
    public String brand;
    public String instruction;
    public String terms;


    @JsonAlias("category_id")
    public long categoryId;

    public ProductDTO(String name, String description, int stock, double price, String imageUrl, String brand, String instruction, String terms, long categoryId) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.imageUrl = imageUrl;
        this.brand = brand;
        this.instruction = instruction;
        this.terms = terms;
        this.categoryId = categoryId;
    }
}
