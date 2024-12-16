package com.himasha.service;

import com.himasha.dto.ResturantDto;
import com.himasha.model.Address;
import com.himasha.model.Resturant;
import com.himasha.model.User;
import com.himasha.repository.AddressRepository;
import com.himasha.repository.ResturantRepository;
import com.himasha.repository.UserRepository;
import com.himasha.request.CreateResturantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResturantServiceImp implements ResturantService {

    @Autowired
    private ResturantRepository resturantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Resturant createResturant(CreateResturantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());

        Resturant resturant = new Resturant();
        resturant.setAddress(address);
        resturant.setContactInformation(req.getContactInformation());
        resturant.setCuisineType(req.getCuisineType());
        resturant.setDescription(req.getDescription());
        resturant.setImages(req.getImages());
        resturant.setName(req.getName());
        resturant.setOpeningHours(req.getOpeningHours()); // Fixed typo
        resturant.setRegistrationDate(LocalDateTime.now());
        resturant.setOwner(user);

        return resturantRepository.save(resturant);
    }

    @Override
    public Resturant updateResturant(Long resturantId, CreateResturantRequest updateResturant) throws Exception {
        Resturant resturant = findResturantById(resturantId);

        if (updateResturant.getCuisineType() != null) {
            resturant.setCuisineType(updateResturant.getCuisineType());
        }
        if (updateResturant.getDescription() != null) {
            resturant.setDescription(updateResturant.getDescription());
        }
        if (updateResturant.getName() != null) {
            resturant.setName(updateResturant.getName());
        }
        return resturantRepository.save(resturant);
    }

    @Override
    public void deleteResturant(Long resturantId) throws Exception {
        Resturant resturant = findResturantById(resturantId);
        resturantRepository.delete(resturant);
    }

    @Override
    public List<Resturant> getAllResturant() {
        return resturantRepository.findAll();
    }

    @Override
    public List<Resturant> searchResturant(String keyword) {
        return resturantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Resturant findResturantById(Long id) throws Exception {
        Optional<Resturant> opt = resturantRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("Restaurant not found with ID: " + id);
        }
        return opt.get();
    }

    @Override
    public Resturant getResturantByUserId(Long userId) throws Exception {
        Resturant resturant = resturantRepository.findByOwnerId(userId);
        if (resturant == null) {
            throw new Exception("Restaurant not found with Owner ID: " + userId);
        }
        return resturant;
    }

    @Override
    public ResturantDto addToFavourite(Long resturantId, User user) throws Exception {
        Resturant resturant = findResturantById(resturantId);
        ResturantDto dto = new ResturantDto();
        dto.setDescription(resturant.getDescription());
        dto.setImages(resturant.getImages());
        dto.setTitle(resturant.getName());

        if (user.getFavourites() != null && user.getFavourites().contains(dto)) {
            user.getFavourites().remove(dto);
        } else {
            if (user.getFavourites() == null) {
                user.setFavourites(new ArrayList<>());
            }
            user.getFavourites().add(String.valueOf(dto));
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public Resturant updateResturantStatus(long id) throws Exception {
        Resturant resturant = findResturantById(id);
        resturant.setOpen(!resturant.isOpen());
        return resturantRepository.save(resturant);
    }
}
