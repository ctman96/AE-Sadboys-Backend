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

    @RequestMapping()
    public ResponseEntity<PagedResources<User>> showUsers(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showRoles'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 1;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<User> pageResult = userRepository.findAll(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Users found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<User> resources = new PagedResources<User>(pageResult.getContent(), metadata);
        return ResponseEntity.ok(resources);
    }

    @RequestMapping( value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<User>> getUser(@PathVariable("id") Integer id){
        User c = userRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("User not found - id: " + id);
        }
        Resource<User> resource = userResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }
    @RequestMapping( value="", params = "userId", method = RequestMethod.GET)
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
        if (c != null) {
            userRepository.save(user);
        }
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