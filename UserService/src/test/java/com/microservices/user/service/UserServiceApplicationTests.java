package com.microservices.user.service;

import com.microservices.user.service.entities.Rating;
import com.microservices.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Test
	void createRating() {
		Rating rating = Rating.builder().userId("123").hotelId("345").feedback("This is created using Feign Client").build();
		Rating savedRating = ratingService.createRating(rating);

		System.out.println("New Rating Created");
	}


}
