package com.microservices.rating.services.impl;

import com.microservices.rating.entities.Hotel;
import com.microservices.rating.entities.Rating;
import com.microservices.rating.external.services.HotelService;
import com.microservices.rating.repositories.RatingRepository;
import com.microservices.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    HotelService hotelService;

    // create
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    // Get all ratings
    @Override
    public List<Rating> getRatings() {
        List<Rating> ratingList = ratingRepository.findAll();
        ratingList.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        return ratingList;
    }

    // get all by User Id
    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    // get all by hotel
    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {

        return ratingRepository.findByHotelId(hotelId);
    }

}
