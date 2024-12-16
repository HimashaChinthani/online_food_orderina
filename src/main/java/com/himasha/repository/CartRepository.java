package com.himasha.repository;

import com.himasha.model.Cart;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByCustomerId(Long id);
}