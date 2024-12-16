package com.himasha.Controller;

import com.himasha.model.Resturant;
import com.himasha.model.User;
import com.himasha.request.CreateResturantRequest;
import com.himasha.response.MessageResponse;
import com.himasha.service.ResturantService;
import com.himasha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/resturants")
public class AdminResturantController {

    @Autowired
    private ResturantService resturantService;

    @Autowired
    private UserService userService;  // Autowired instance

    @PostMapping
    public ResponseEntity<Resturant> createResturant(
            @RequestBody CreateResturantRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        // Fix: userService should be accessed as an instance variable, not static
        User user = userService.findUserByJwtToken(jwt);  // Corrected to use the injected userService instance

        Resturant resturant = resturantService.createResturant(req, user);
        return new ResponseEntity<>(resturant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resturant> updateResturant(
            @RequestBody CreateResturantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);  // Corrected to use the injected userService instance

        Resturant resturant = resturantService.updateResturant(id, req);
        return new ResponseEntity<>(resturant, HttpStatus.OK);  // Use HttpStatus.OK for updates
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteResturant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);  // Corrected to use the injected userService instance

        resturantService.deleteResturant(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Resturant deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);  // Changed to HttpStatus.OK
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Resturant> updateResturantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);  // Corrected to use the injected userService instance

        // Call the service method to update the status
        Resturant resturant = resturantService.updateResturantStatus(id);

        return new ResponseEntity<>(resturant, HttpStatus.OK);  // Correct response status for updates
    }

    @GetMapping("/user")
    public ResponseEntity<Resturant> findResturantByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);  // Corrected to use the injected userService instance

        Resturant resturant = resturantService.getResturantByUserId(user.getId());

        return new ResponseEntity<>(resturant, HttpStatus.OK);
    }
}
