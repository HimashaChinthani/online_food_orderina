package com.himasha.Controller;

import com.himasha.model.Food;
import com.himasha.model.Resturant;
import com.himasha.model.User;
import com.himasha.request.CreateFoodRequest;
import com.himasha.service.FoodService;
import com.himasha.service.ResturantService;
import com.himasha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;  // Use the injected instance of UserService

    @Autowired
    private ResturantService resturantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String name,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        // Corrected: Use userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Use the injected instance of userService

        List<Food> foods = foodService.searcgFood(name);  // Assuming the search method name is correct
        return new ResponseEntity<>(foods, HttpStatus.OK);  // Changed to OK status
    }

    @GetMapping("/resturant/{resturantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vagetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long resturantId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        // Corrected: Use userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Use the injected instance of userService

        List<Food> foods = foodService.getResturantsFood(
                resturantId, vagetarian, nonveg, seasonal, food_category
        );
        return new ResponseEntity<>(foods, HttpStatus.OK);  // Changed to OK status
    }
}
