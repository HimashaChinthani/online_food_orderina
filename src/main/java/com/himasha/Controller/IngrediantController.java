package com.himasha.Controller;


import com.himasha.model.IngrediantCategory;
import com.himasha.model.IngredientsItem;
import com.himasha.request.IngrediantCategoryRequest;
import com.himasha.request.IngredientRequest;
import com.himasha.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/ingrediants")
public class IngrediantController {
    @Autowired
    private IngredientsService ingredientsService;
    @PostMapping("/category")
    public ResponseEntity<IngrediantCategory>createIngrediantCategory(
            @RequestBody IngrediantCategoryRequest req
    ) throws Exception {
        IngrediantCategory item=ingredientsService.createIngredientCategory(req.getName(),req.getResturantId());
        return  new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest req
    ) throws Exception {
        IngredientsItem item = ingredientsService.createIngredientItem(req.getName(), req.getRestaurantId(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
           @PathVariable Long id
    ) throws Exception {
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @GetMapping("/resturant/{id}")
    public ResponseEntity<List<IngredientsItem>> getResturantIngredient(
            @PathVariable Long id
    ) throws Exception {
       List< IngredientsItem> items = ingredientsService.findResturantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/resturant/{id}/category")
    public ResponseEntity<List<IngrediantCategory>> getResturantIngredientCategory(
            @PathVariable Long id
    ) throws Exception {
        List<IngrediantCategory> items = (List<IngrediantCategory>) ingredientsService.findIngredientCategoryById(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }







}

