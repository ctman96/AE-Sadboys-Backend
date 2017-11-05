package com.ipfms.controllers;

import com.ipfms.assembler.LocationResourceAssembler;
import com.ipfms.domain.model.Location;
import com.ipfms.domain.repository.LocationRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Location>> showClassHierarchies() {
        List<Location> c = (ArrayList<Location>) locationRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No Locations found");
        }
        Resources<Location> resources = locationResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Location>> getLocation(@PathVariable("id") Integer id){
        Location c = locationRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Location not found - id: " + id);
        }
        Resource<Location> resource = locationResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createLocation(@RequestBody Location location) {
        locationRepository.save(location);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteLocation(@PathVariable("id") Integer id){
        locationRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}