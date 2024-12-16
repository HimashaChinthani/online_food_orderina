package com.himasha.repository;

import com.himasha.model.Resturant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResturantRepository extends JpaRepository<Resturant,Long> {
@Query("SELECT r FROM Resturant r WHERE lower(r.name) LIKE lower(concat('%',:query,'%')) OR lower(r.cuisineType) LIKE lower(concat('%',:query,'%') )")
List<Resturant> findBySearchQuery(String query);
    Resturant findByOwnerId(Long userId);
}
