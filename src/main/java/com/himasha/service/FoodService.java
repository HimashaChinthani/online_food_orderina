package com.himasha.service;

import com.himasha.model.Category;
import com.himasha.model.Food;
import com.himasha.model.Resturant;
import com.himasha.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Resturant resturant);

    Food deleteFood(Long foodId) throws Exception;


    public List<Food> getResturantsFood(Long  resturantId,boolean isVegitarian,boolean isNonveg,boolean isSeasonal,String foodCategory);
    public List<Food> searcgFood(String keyword);
    public Food findFoodById(Long foodId)throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;


}
