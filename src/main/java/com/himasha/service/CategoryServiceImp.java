package com.himasha.service;

import com.himasha.model.Cart;
import com.himasha.model.Category;
import com.himasha.model.Resturant;
import com.himasha.model.User;
import com.himasha.repository.CartRepository;
import com.himasha.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private ResturantService resturantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Resturant resturant = resturantService.getResturantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setResturant(resturant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByResturantId(Long id) throws Exception {
        Resturant resturant = resturantService.findResturantById(id);
        return categoryRepository.findByResturantId(resturant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new Exception("Category not found");
        }
        return optionalCategory.get();
    }

    private Cart findCartByUserId(Long userId) {
        return cartRepository.findByCustomerId(userId); // Corrected to use cartRepository
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt); // Corrected to use injected instance of userService
        // Corrected to call userService

        Cart cart = findCartByUserId(user.getId()); // Fixed method name and usage

        if (cart == null) {
            throw new Exception("Cart not found for user.");
        }

        cart.getItems().clear(); // Clear the items in the cart

        return cartRepository.save(cart); // Save the updated cart
    }
}

