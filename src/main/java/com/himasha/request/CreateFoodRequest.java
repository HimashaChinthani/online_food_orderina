package com.himasha.request;

import com.himasha.model.Category;
import com.himasha.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private  Long price;

    private Category category;
    private List<String> images;

    private Long restutrantId;
    private boolean vegetarian;
    private boolean seasional;
    private List<IngredientsItem> ingredient;

}
