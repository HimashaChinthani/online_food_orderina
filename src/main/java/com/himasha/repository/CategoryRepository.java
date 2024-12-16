package com.himasha.repository;

import com.himasha.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Finds all categories by the restaurant ID
    List<Category> findByResturantId(Long id);
}
