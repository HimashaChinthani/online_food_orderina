package com.himasha.repository;

import com.himasha.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository  extends JpaRepository<IngredientsItem,Long> {

    List<IngredientsItem> findByResturantId(Long id);
}
