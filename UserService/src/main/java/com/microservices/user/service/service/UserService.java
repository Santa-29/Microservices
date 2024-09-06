package com.microservices.user.service.service;

import com.microservices.user.service.entities.User;
import com.microservices.user.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    // user operations

    // create
    User saveUser(User user);

    // get all user
    List<User> getAllUser();

    // get single user of given user id
    User getUser(String userId);

    // Delete
    User deleteUser(User user);

    // Update
    User updateUser(User user);


}
