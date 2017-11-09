package com.ipfms.controllers;

import com.ipfms.assembler.RoleResourceAssembler;
import com.ipfms.domain.model.Role;
import com.ipfms.domain.repository.RoleRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @RequestMapping()
    public ResponseEntity<Page<Role>> showRoles(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showRoles'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Role> pageResult = roleRepository.findAll(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Roles found");
        }
        //TODO:
        //List<Resource<Role>> resources = roleResourceAssembler.toResources(c);
        System.out.println("Exiting 'showRoles'");
        return ResponseEntity.ok(pageResult);
    }


    @RequestMapping( value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Role>> getRole(@PathVariable("id") Integer id){
        Role c = roleRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Role not found - id: " + id);
        }
        Resource<Role> resource = roleResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    //TODO
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