package com.example.webshop.dao;

import org.springframework.stereotype.Repository;
import com.example.webshop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    public List<Order> findByUserId(long id);



}
