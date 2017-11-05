package com.ipfms.controllers;

import com.ipfms.assembler.UserResourceAssembler;
import com.ipfms.domain.model.User;
import com.ipfms.domain.repository.UserRepository;
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
@RequestMapping("/users")
public class UserController{

    private final UserRepository userRepository;
    private final UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserController(UserResourceAssembler resourceAssembler, UserRepository repository){
        this.userRepository = repository;
        this.userResourceAssembler = resourceAssembler;
    }

    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<User>> showClassHierarchies() {
        List<User> c = (ArrayList<User>) userRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No Users found");
        }
        Resources<User> resources = userResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<User>> getUser(@PathVariable("id") Integer id){
        User c = userRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("User not found - id: " + id);
        }
        Resource<User> resource = userResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }
    @RequestMapping(produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<Resource<User>> getUser(@RequestParam("userId") String userId){
        User c = userRepository.findByUserId(userId);
        if (c == null) {
            throw new EntityNotFoundException("User not found - userId: " + userId);
        }
        Resource<User> resource = userResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> updateUser(@RequestBody User user) {
        User c = userRepository.findByUserId(user.getUserId());
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}