package com.ipfms.controllers;

import com.ipfms.assembler.UserResourceAssembler;
import com.ipfms.domain.model.User;
import com.ipfms.domain.repository.UserRepository;
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
@RequestMapping("/users")
public class UserController{

    private final UserRepository userRepository;
    private final UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserController(UserResourceAssembler resourceAssembler, UserRepository repository){
        this.userRepository = repository;
        this.userResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<User> showUsers() {
        return (ArrayList<User>)userRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<User>> getUser(@PathVariable("id") Integer id){
        User c = userRepository.findById(id);
        Resource<User> resource = userResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
