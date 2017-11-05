package com.ipfms.controllers;

import com.ipfms.assembler.RoleResourceAssembler;
import com.ipfms.domain.model.Role;
import com.ipfms.domain.repository.RoleRepository;
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
@RequestMapping("/roles")
public class RoleController{

    private final RoleRepository roleRepository;
    private final RoleResourceAssembler roleResourceAssembler;

    @Autowired
    public RoleController(RoleResourceAssembler resourceAssembler, RoleRepository repository){
        this.roleRepository = repository;
        this.roleResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<Role> showRoles() {
        return (ArrayList<Role>)roleRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Role>> getRole(@PathVariable("id") Integer id){
        Role c = roleRepository.findById(id);
        Resource<Role> resource = roleResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
