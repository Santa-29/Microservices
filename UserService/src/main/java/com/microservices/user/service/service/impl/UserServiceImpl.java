package com.microservices.user.service.service.impl;

import com.microservices.user.service.entities.Hotel;
import com.microservices.user.service.entities.Rating;
import com.microservices.user.service.entities.User;
import com.microservices.user.service.exceptions.ResourceNotFoundException;
import com.microservices.user.service.repositories.UserRepository;
import com.microservices.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // Get User from database with the help of user Repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

        // fetch rating of above user from Rating Service
        // http://localhost:8083/ratings/users/1a3baeb4-2f39-46c8-8275-e8023cb9ffa2
        // Call that api from userService
        String url = "http://RATINGSERVICE/ratings/users/" + userId;
        Rating[] ratingOfUsers = restTemplate.getForObject(url, Rating[].class);


        logger.info(ratingOfUsers + "");


        // for each rating find hotel
        List<Rating> ratingList = Arrays.stream(ratingOfUsers).filter(x -> x != null).map(rating -> {
            // api call to hotel service to get the hotel
            // http://localhost:8081/hotels/877ba88b-934b-44d2-8197-0607ea5ce472
            String hotelUrl = "http://HOTELSERVICE/hotels/" + rating.getHotelId();
            ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity(hotelUrl, Hotel.class);
            Hotel hotel = hotelEntity.getBody();

            // set the hotel to rating
            rating.setHotel(hotel);
            // return the rating
            return rating;


        }).collect(Collectors.toList());


        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User deleteUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }


}
