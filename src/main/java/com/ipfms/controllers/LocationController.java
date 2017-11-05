package com.ipfms.controllers;

import com.ipfms.assembler.LocationResourceAssembler;
import com.ipfms.domain.model.Location;
import com.ipfms.domain.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/locations")
public class LocationController{

    private final LocationRepository locationRepository;
    private final LocationResourceAssembler locationResourceAssembler;

    @Autowired
    public LocationController(LocationResourceAssembler resourceAssembler, LocationRepository repository){
        this.locationRepository = repository;
        this.locationResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<Location> showLocations() {
        return (ArrayList<Location>)locationRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Location>> getLocation(@PathVariable("id") Integer id){
        Location c = locationRepository.findById(id);
        Resource<Location> resource = locationResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
