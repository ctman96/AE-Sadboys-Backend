package com.ipfms.controllers;

import com.ipfms.assembler.RoleResourceAssembler;
import com.ipfms.domain.model.Role;
import com.ipfms.domain.repository.RoleRepository;
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
@RequestMapping("/roles")
public class RoleController{

    private final RoleRepository roleRepository;
    private final RoleResourceAssembler roleResourceAssembler;

    @Autowired
    public RoleController(RoleResourceAssembler resourceAssembler, RoleRepository repository){
        this.roleRepository = repository;
        this.roleResourceAssembler = resourceAssembler;
    }

    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Role>> showClassHierarchies() {
        List<Role> c = (ArrayList<Role>) roleRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No Roles found");
        }
        Resources<Role> resources = roleResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Role>> getRole(@PathVariable("id") Integer id){
        Role c = roleRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Role not found - id: " + id);
        }
        Resource<Role> resource = roleResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createRole(@RequestBody Role role) {
        roleRepository.save(role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteRole(@PathVariable("id") Integer id){
        roleRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}