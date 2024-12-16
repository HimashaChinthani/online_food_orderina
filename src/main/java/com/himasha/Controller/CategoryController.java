package com.himasha.Controller;

import com.himasha.model.Category;
import com.himasha.model.User;
import com.himasha.service.CategoryService;
import com.himasha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;  // Autowired instance of UserService

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestHeader("Authorization") String jwt) throws Exception {
        // Use userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Corrected: Use injected userService instance
        Category createCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @GetMapping("/admin/resturant")
    public ResponseEntity<List<Category>> getResturantCategory(@RequestHeader("Authorization") String jwt) throws Exception {
        // Use userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Corrected: Use injected userService instance
        List<Category> categories = (List<Category>) categoryService.findCategoryById(user.getId());  // Removed unnecessary cast

        return new ResponseEntity<>(categories, HttpStatus.OK);  // Changed to HttpStatus.OK for GET requests
    }
}
