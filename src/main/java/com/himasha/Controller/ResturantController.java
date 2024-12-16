package com.himasha.Controller;

import com.himasha.dto.ResturantDto;
import com.himasha.model.Resturant;
import com.himasha.model.User;
import com.himasha.request.CreateResturantRequest;
import com.himasha.service.ResturantService;
import com.himasha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resturants")
public class ResturantController {

    @Autowired
    private ResturantService resturantService;

    @Autowired
    private UserService userService;  // Autowired instance of UserService

    @GetMapping("/search")
    public ResponseEntity<List<Resturant>> searchResturant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception {
        // Corrected: Use userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Use the injected instance of userService

        List<Resturant> resturants = resturantService.searchResturant(keyword);
        return new ResponseEntity<>(resturants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resturant> findResturantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        // Corrected: Use userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Use the injected instance of userService

        Resturant resturant = resturantService.findResturantById(id);
        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favourites")
    public ResponseEntity<ResturantDto> addToFavourites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        // Corrected: Use userService instance to call the non-static method
        User user = userService.findUserByJwtToken(jwt);  // Use the injected instance of userService

        ResturantDto resturantDto = resturantService.addToFavourite(id, user);
        return new ResponseEntity<>(resturantDto, HttpStatus.OK);
    }
}
