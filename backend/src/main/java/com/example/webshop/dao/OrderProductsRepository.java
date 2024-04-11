package com.example.webshop.dao;

import com.example.webshop.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProduct, Long> {
}
