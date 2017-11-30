package com.ipfms.controllers;

import com.ipfms.assembler.LocationResourceAssembler;
import com.ipfms.domain.model.Location;
import com.ipfms.domain.model.User;
import com.ipfms.domain.repository.LocationRepository;
import com.ipfms.domain.repository.UserRepository;
import com.ipfms.exception.EntityNotFoundException;
import com.ipfms.exception.UnauthorizedCallException;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Rest Controller -
 * Handles RequestMapping for the '/locations' namespace
 */
@RestController
@RequestMapping("/locations")
public class LocationController{

    private final LocationRepository locationRepository;
    private final LocationResourceAssembler locationResourceAssembler;
    private final UserRepository userRepository;

    @Autowired
    public LocationController(LocationResourceAssembler resourceAssembler, LocationRepository repository,
                              UserRepository userRepository){
        this.locationRepository = repository;
        this.locationResourceAssembler = resourceAssembler;
        this.userRepository = userRepository;
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
    ResponseEntity<Void> saveLocation(HttpServletRequest request, @RequestBody Location location) {

        String userId = (String)request.getAttribute("currentUserId");
        User cur = userRepository.findByUserId(userId);
        if (cur == null) {
            throw new EntityNotFoundException("User not found - id: " + userId);
        }
        if (!cur.isAdmin()){
            throw new UnauthorizedCallException("User lacks the necessary role for this call: Admin");
        }

        Location l = null;
        if(location.getId() != null){
            l = locationRepository.findById(location.getId());
        }

        if(l!= null){
            if(l.isLocked() && !l.getUsers().contains(cur)){
                throw new UnauthorizedCallException("This user is not permitted to edit this location");
            }
        }


        Set<User> requestUsers = location.getUsers();
        if (l != null) {
            Set<User> oldUsers = l.getUsers();

            if (oldUsers == null) {
                oldUsers = new HashSet<>();
            }
            Set<User> newUsers = new HashSet<>();

            if (requestUsers != null) {
                for (User u : requestUsers) {
                    User user = null;
                    if (u.getId() != null) {
                        user = userRepository.findById(u.getId());
                    } else if (u.getUserId() != null) {
                        user = userRepository.findByUserId(u.getUserId());
                    } else {
                        throw new EntityNotFoundException("No valid identifier provided for user");
                    }
                    if (user == null) {
                        throw new EntityNotFoundException("User not found - id: " + u.getId());
                    }
                    newUsers.add(user);
                }
            }

            System.out.println("New users: " + newUsers);
            System.out.println("Old users: " + oldUsers);

            Set<User> usersToAdd = new HashSet<>();
            usersToAdd.addAll(newUsers);
            Set<User> usersToRemove = new HashSet<>();
            usersToRemove.addAll(oldUsers);
            usersToRemove.removeAll(newUsers);

            System.out.println("Users to Add: " + usersToAdd);
            System.out.println("Users to Remove: " + usersToRemove);

            newUsers = new HashSet<>();

            if (usersToAdd != null) {
                for (User u : usersToAdd) {
                    Set<Location> userLocation = u.getLocations();
                    userLocation.add(l);
                    u.setLocations(userLocation);
                    newUsers.add(u);
                    userRepository.save(u);
                }
            }
            if (usersToRemove != null) {
                for (User u : usersToRemove) {
                    Set<Location> userLocations = u.getLocations();
                    userLocations.remove(l);
                    u.setLocations(userLocations);
                    userRepository.save(u);
                }
            }
            location.setUsers(newUsers);
        }
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
    ResponseEntity<Void> deleteLocation(HttpServletRequest request, @PathVariable("id") Integer id){
        String userId = (String)request.getAttribute("currentUserId");
        User cur = userRepository.findByUserId(userId);
        if (cur == null) {
            throw new EntityNotFoundException("User not found - id: " + userId);
        }
        if (!cur.isAdmin()){
            throw new UnauthorizedCallException("User lacks the necessary role for this call: Admin");
        }

        locationRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}