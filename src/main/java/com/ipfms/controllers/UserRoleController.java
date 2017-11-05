package com.ipfms.controllers;

import com.ipfms.domain.model.UserRole;
import com.ipfms.domain.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userRoles")
public class UserRoleController{

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleController(UserRoleRepository repository){
        this.userRoleRepository = repository;
    }

    @RequestMapping
    public List<UserRole> showUserRoles() {
        return (ArrayList<UserRole>)userRoleRepository.findAll();
    }


    //TODO: Not sure how to handle yet
    /*@RequestMapping(value="/{id}", method = RequestMethod.GET)
    UserRole getUserRole(@PathVariable("id") Integer id){
        return userRoleRepository.findById(id);
    }*/
}
