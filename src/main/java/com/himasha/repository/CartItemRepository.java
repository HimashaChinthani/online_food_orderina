package com.himasha.repository;

import com.himasha.model.Cart;
import com.himasha.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    public Cart findByCustomerId(Long userId);


}
