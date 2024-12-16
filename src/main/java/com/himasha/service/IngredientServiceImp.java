package com.himasha.service;


import com.himasha.model.IngrediantCategory;
import com.himasha.model.IngredientsItem;
import com.himasha.model.Resturant;
import com.himasha.repository.IngredientCategoryRepository;
import com.himasha.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientsService{
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    private  ResturantService resturantService;

    @Override
    public IngrediantCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Resturant resturant=resturantService.findResturantById(restaurantId);

        IngrediantCategory category=new IngrediantCategory();
        category.setResturant(resturant);
        category.setName(name);


        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngrediantCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngrediantCategory> opt=ingredientCategoryRepository.findById(id);

        if(opt.isEmpty()){
            throw  new Exception("Ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngrediantCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        resturantService.findResturantById(id);
        return IngredientCategoryRepository.findByResturantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(String ingredientname, Long resturantId, Long categoryId) throws Exception {
        Resturant resturant=resturantService.findResturantById(resturantId);
        IngrediantCategory category=findIngredientCategoryById(categoryId);

        IngredientsItem item=new IngredientsItem();
        item.setName(ingredientname);
        item.setResturant(resturant);
        item.setCategory(category);

        IngredientsItem ingredient = ingredientItemRepository.save(item);
        category.getIngredients().add(ingredient);

        return ingredient;
    }

    @Override
    public List<IngredientsItem> findResturantsIngredients(Long resturantId) {

        return ingredientItemRepository.findByResturantId(resturantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem=ingredientItemRepository.findById(id);

        if(optionalIngredientsItem.isEmpty()){
            throw  new Exception("Ingredient not found");
        }
        IngredientsItem ingredientsItem=optionalIngredientsItem.get();
        ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
        return ingredientItemRepository.save(ingredientsItem);
    }
}
