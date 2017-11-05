package com.ipfms.controllers;

import com.ipfms.domain.model.UserLocation;
import com.ipfms.domain.repository.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userLocations")
public class UserLocationController{

    private final UserLocationRepository userLocationRepository;

    @Autowired
    public UserLocationController(UserLocationRepository repository){
        this.userLocationRepository = repository;
    }

    @RequestMapping
    public List<UserLocation> showUserLocations() {
        return (ArrayList<UserLocation>)userLocationRepository.findAll();
    }

    //TODO: Not sure how to handle this yet
    /*@RequestMapping(value="/{id}", method = RequestMethod.GET)
    UserLocation getUserLocation(@PathVariable("id") Integer id){
        return userLocationRepository.findById(id);
    }*/

}
