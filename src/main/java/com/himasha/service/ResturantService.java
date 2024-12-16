package com.himasha.service;

import com.himasha.dto.ResturantDto;
import com.himasha.model.Resturant;
import com.himasha.model.User;
import com.himasha.request.CreateResturantRequest;

import java.util.List;

public interface ResturantService {
    public Resturant createResturant(CreateResturantRequest req, User user);

    public Resturant updateResturant(Long resturantId,CreateResturantRequest updateResturant)throws Exception;

    public void deleteResturant(Long resturantId)throws Exception;

    public List<Resturant> getAllResturant();

    public List<Resturant> searchResturant(String keyword);

    public Resturant findResturantById(Long id)throws  Exception;

    public Resturant getResturantByUserId(Long userId)throws Exception;

    public ResturantDto addToFavourite(Long resturantId,User user)throws  Exception;

    public Resturant updateResturantStatus(long id)throws  Exception;

}
