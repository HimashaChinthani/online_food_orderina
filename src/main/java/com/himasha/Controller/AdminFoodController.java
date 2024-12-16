package com.himasha.Controller;

import com.himasha.model.Food;
import com.himasha.model.Resturant;
import com.himasha.model.User;
import com.himasha.request.CreateFoodRequest;
import com.himasha.response.MessageResponse;
import com.himasha.service.FoodService;
import com.himasha.service.ResturantService;
import com.himasha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResturantService resturantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        // Use the injected userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Corrected: Use userService instance
        Resturant resturant = resturantService.findResturantById(req.getRestutrantId());
        Food food = foodService.createFood(req, req.getCategory(), resturant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        // Use the injected userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Corrected: Use userService instance

        foodService.deleteFood(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("Food deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);  // Corrected to HTTP 200 OK instead of 201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        // Use the injected userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Corrected: Use userService instance

        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);  // Corrected to HTTP 200 OK instead of 201 CREATED
    }
}

