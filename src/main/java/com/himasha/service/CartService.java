package com.himasha.service;

import com.himasha.model.Cart;
import com.himasha.model.CartItem;
import com.himasha.model.User;
import com.himasha.request.AddCartItemRequest;
import lombok.Data;


public interface CartService {

    CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    Long calculateCartTotals(Cart cart) throws Exception; // Removed duplicate method declaration

    Cart findCartById(Long id) throws Exception;

    Cart clearCart(Long userId) throws Exception;

    Cart findCartByUserId(Long userId) throws Exception;


}
