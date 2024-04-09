package com.example.webshop.dao;

import com.example.webshop.models.Webshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebshopRepository extends JpaRepository<Webshop, Long> {
    Webshop findByPrefix(String prefix);
}

