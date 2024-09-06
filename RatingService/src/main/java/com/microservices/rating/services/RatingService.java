package com.microservices.rating.services;

import com.microservices.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {
    // create
    Rating create(Rating rating);

    // Get all ratings
    List<Rating> getRatings();

    // get all by User Id
    List<Rating> getRatingByUserId(String userId);

    // get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
