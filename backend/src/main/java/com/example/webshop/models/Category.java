package com.example.webshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    /*
    maps the one-to-many relationship between category and products, jsonmanaged so that we do not get an
    infinite dependency loop in the request.
     */
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private Set<Product> products;

    //needed by JPA to create the entity must be present no arg constructor
    public Category() {
    }

    //getters and setters are needed to map all the properties to the database by JPA, could
    //also be solved by making the properties public but gives less control over the properties.
    public Category(String name) {
        this.name = name;
        this.products = null;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
