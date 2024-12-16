package com.himasha.repository;

import com.himasha.model.IngrediantCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngrediantCategory,Long> {

    static List<IngrediantCategory> findByResturantId(Long id) {
        return null;
    }
}
