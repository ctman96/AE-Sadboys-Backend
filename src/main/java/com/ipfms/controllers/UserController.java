package com.ipfms.controllers;

import com.ipfms.assembler.UserResourceAssembler;
import com.ipfms.domain.model.User;
import com.ipfms.domain.repository.UserRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Rest Controller -
 * Handles RequestMapping for the '/users' namespace
 */
@RestController
@RequestMapping("/users")
public class UserController{

    private final UserRepository userRepository;
    private final UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserController(UserResourceAssembler resourceAssembler, UserRepository repository){
        this.userRepository = repository;
        this.userResourceAssembler = resourceAssembler;
    }

    /**
     * Returns a ResponseEntity object containing a page of Users, as HATEOAS PagedResources,
     * with the optionally specified page parameters. The optional size argument
     * specifies the page's size, must be an integer value. Defaults to '10'.
     * The optional page argument specifies the page number. Must be an integer,
     * Defaults to '0'
     * <p>
     * This is mapped to the '/users' route
     *
     * @param size  the size of pages you want returned (optional, default 10)
     * @param page  the page number, for the given size (optional, default 0)
     * @return      the ResponseEntity containing the page of User Hateoas Resources
     */
    @RequestMapping()
    public ResponseEntity<PagedResources<User>> showUsers(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showUsers'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 1;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<User> pageResult = userRepository.findByOrderByUserIdAsc(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Users found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<User> resources = new PagedResources<>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showUsers'");
        return ResponseEntity.ok(resources);
    }

    /**
     * Returns a Response Entity containing a User object, as a HATEOAS Resource,
     * with an id value matching the given id parameter. The id
     * argument corresponds to the 'id' PathVariable from the
     * RequestMapping, '/users/{id}'
     *
     * @param id   the id value of the User you are requesting
     * @return      Response Entity containing the corresponding User, as a HATEOAS Resource
     */
    @RequestMapping( value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<User>> getUser(@PathVariable("id") Integer id){
        User c = userRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("User not found - id: " + id);
        }
        Resource<User> resource = userResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }
    /**
     * Returns a Response Entity containing a User object, as a HATEOAS Resource,
     * with an userId value matching the given userId parameter. The userId
     * argument corresponds to the 'userId' RequestParameter
     * <p>
     * This is mapped to the '/users' route
     *
     * @param userId    the userId value of the User you are requesting
     * @return          Response Entity containing the corresponding User, as a HATEOAS Resource
     */
    @RequestMapping( value="", params = "userId", method = RequestMethod.GET)
    ResponseEntity<Resource<User>> getUser(@RequestParam("userId") String userId){
        User c = userRepository.findByUserId(userId);
        if (c == null) {
            throw new EntityNotFoundException("User not found - userId: " + userId);
        }
        Resource<User> resource = userResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    /**
     * Attempts to Update a User in the UserRepository.
     * user argument is a User object.
     * The UserRepository will attempt to save this object. If the user argument's
     * userId cannot be found, this method fails
     * <p>
     * Mapped to the '/users' route POST request
     *
     * @param user the User object to be created/updated
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> updateUser(@RequestBody User user) {
        User c = userRepository.findByUserId(user.getUserId());
        if (c == null) {
            throw new EntityNotFoundException("User not found - id: " + user.getUserId());
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/currentuser", method = RequestMethod.GET)
    ResponseEntity<Resource<User>> getCurrentUser(HttpServletRequest request){
        String userId = (String)request.getAttribute("currentUserId");
        User cur = userRepository.findByUserId(userId);
        if (cur == null) {
            throw new EntityNotFoundException("User not found - id: " + userId);
        }
        Resource<User> resource = userResourceAssembler.toResource(cur);
        return ResponseEntity.ok(resource);
    }
}