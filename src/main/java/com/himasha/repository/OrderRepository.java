package com.himasha.repository;

import com.himasha.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findByCustomerId(Long userId);

    public  List<Order> findByResturantId(Long resturantId);



    public void deleteOrderById(Long orderId);
}
