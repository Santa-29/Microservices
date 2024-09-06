package com.microservices.user.service.external.services;

import com.microservices.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    // GET

    // POST
    @PostMapping("/ratings")
    Rating createRating(Rating values);

    // PUT
    @PutMapping ("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);

}
