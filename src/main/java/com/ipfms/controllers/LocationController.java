package com.ipfms.controllers;

import com.ipfms.assembler.LocationResourceAssembler;
import com.ipfms.domain.model.Location;
import com.ipfms.domain.repository.LocationRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller -
 * Handles RequestMapping for the '/locations' namespace
 */
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

    /**
     * Returns a ResponseEntity object containing a page of Location, as HATEOAS PagedResources,
     * with the optionally specified page parameters. The optional size argument
     * specifies the page's size, must be an integer value. Defaults to '10'.
     * The optional page argument specifies the page number. Must be an integer,
     * Defaults to '0'
     * <p>
     * This is mapped to the '/locations' route
     *
     * @param size  the size of pages you want returned (optional, default 10)
     * @param page  the page number, for the given size (optional, default 0)
     * @return      the ResponseEntity containing the page of Location Hateoas Resources
     */
    @RequestMapping()
    public ResponseEntity<PagedResources<Location>> showLocations(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showLocations'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Location> pageResult = locationRepository.findByOrderByNameAsc(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Locations found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<Location> resources = new PagedResources<Location>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showLocations'");
        return ResponseEntity.ok(resources);
    }

    /**
     * Returns a ResponseEntity containing all Location Objects as HATEOAS Resources.
     * <p>
     * Mapped to the the '/locations/all' route
     *
     * @return ResponseEntity containing all Location, as Hateoas Resources
     */
    @RequestMapping("/all")
    public ResponseEntity<Resources<Location>> showAllLocations() {
        System.out.println("In 'showAllLocations'");
        Iterable<Location> locations = locationRepository.findAll();
        if (locations == null) {
            throw new EntityNotFoundException("No Roles found");
        }
        Resources<Location> resources = new Resources<>(locations);
        System.out.println("Exiting 'showAllLocations'");
        return ResponseEntity.ok(resources);
    }

    /**
     * Returns a Response Entity containing a Location object, as a HATEOAS Resource,
     * with an id value matching the given id parameter. The id
     * argument corresponds to the 'id' PathVariable from the
     * RequestMapping, '/locations/{id}'
     *
     * @param id   the id value of the Location you are requesting
     * @return      Response Entity containing the corresponding Location, as a HATEOAS Resource
     */
    @RequestMapping( value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Location>> getLocation(@PathVariable("id") Integer id){
        Location c = locationRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Location not found - id: " + id);
        }
        Resource<Location> resource = locationResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    //TODO
    /**
     * Attempts to Create or Update a Location in the LocationRepository.
     * location argument is a Location object with an optional id field.
     * The LocationRepository will attempt to save this object. If given an
     * id value, it will attempt to update the Location with corresponding id.
     * If id is not given, will create a new Location with a generated Id and specified values.
     * <p>
     * Mapped to the '/locations' route POST request
     *
     * @param location the Location object to be created/updated
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createLocation(@RequestBody Location location) {
        locationRepository.save(location);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Attempts to delete a Location from the LocationRepository.
     * id argument is an integer specifying the id of the Location you wish to
     * delete.
     * @param id the id of the Location to be deleted
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteLocation(@PathVariable("id") Integer id){
        locationRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}