package com.himasha.request;

import com.himasha.model.Address;
import lombok.Data;
import java.util.List;

@Data
public class CreateResturantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address; // Ensure this matches the type in your `Resturant` model
    private String contactInformation;
    private String openingHours;
    private List<String> images;
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public Address getAddress() {
        return address;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public List<String> getImages() {
        return images;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
