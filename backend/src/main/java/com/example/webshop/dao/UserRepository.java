package com.example.webshop.dao;

import com.example.webshop.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {

    public CustomUser findByEmail(String email);

    public CustomUser findById(long id);
}
