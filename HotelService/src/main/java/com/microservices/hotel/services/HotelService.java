package com.microservices.hotel.services;

import com.microservices.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    // create
    Hotel create(Hotel hotel);


    // get All
    List<Hotel> getAll();

    // get
    Hotel get(String id);
}
