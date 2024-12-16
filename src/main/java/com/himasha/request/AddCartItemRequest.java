package com.himasha.request;

import lombok.Data;

import java.util.List;
@Data
public class AddCartItemRequest {
    private  Long foodId;
    public int quantity;
    private List<String> ingredient;


    public List<String> getIngredients() {
        return List.of();
    }
}
