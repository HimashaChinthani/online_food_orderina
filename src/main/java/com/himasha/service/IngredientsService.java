package com.himasha.service;

import com.himasha.model.IngrediantCategory;
import com.himasha.model.IngrediantCategory;
import com.himasha.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngrediantCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngrediantCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngrediantCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;



    public  IngredientsItem createIngredientItem(String ingredientname, Long resturantId,Long categoryId) throws Exception;

   public List<IngredientsItem> findResturantsIngredients(Long resturantId);

   public IngredientsItem updateStock(Long id) throws  Exception;



}

