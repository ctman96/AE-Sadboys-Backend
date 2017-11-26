package com.ipfms.controllers;

import com.ipfms.assembler.RoleResourceAssembler;
import com.ipfms.domain.model.Role;
import com.ipfms.domain.model.User;
import com.ipfms.domain.repository.RoleRepository;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Rest Controller -
 * Handles RequestMapping for the '/roles' namespace
 */
@RestController
@RequestMapping("/roles")
public class RoleController{

    private final RoleRepository roleRepository;
    private final RoleResourceAssembler roleResourceAssembler;
    private final UserRepository userRepository;

    @Autowired
    public RoleController(RoleResourceAssembler resourceAssembler, RoleRepository repository, UserRepository userRepository){
        this.roleRepository = repository;
        this.roleResourceAssembler = resourceAssembler;
        this.userRepository = userRepository;
    }

    /**
     * Returns a ResponseEntity object containing a page of Roles, as HATEOAS PagedResources,
     * with the optionally specified page parameters. The optional size argument
     * specifies the page's size, must be an integer value. Defaults to '10'.
     * The optional page argument specifies the page number. Must be an integer,
     * Defaults to '0'
     * <p>
     * This is mapped to the '/roles' route
     *
     * @param size  the size of pages you want returned (optional, default 10)
     * @param page  the page number, for the given size (optional, default 0)
     * @return      the ResponseEntity containing the page of Role Hateoas Resources
     */
    @RequestMapping()
    ResponseEntity<PagedResources<Role>> showRoles(
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
        Page<Role> pageResult = roleRepository.findByOrderByNameAsc(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Roles found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<Role> resources = new PagedResources<Role>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showRoles'");
        return ResponseEntity.ok(resources);
    }

    /**
     * Returns a Response Entity containing a Role object, as a HATEOAS Resource,
     * with an id value matching the given id parameter. The id
     * argument corresponds to the 'id' PathVariable from the
     * RequestMapping, '/roles/{id}'
     *
     * @param id   the id value of the Role you are requesting
     * @return      Response Entity containing the corresponding Role, as a HATEOAS Resource
     */
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
    /**
     * Attempts to Create or Update a Role in the RoleRepository.
     * role argument is a Role object with an optional id field.
     * The RoleRepository will attempt to save this object. If given an
     * id value, it will attempt to update the Role with corresponding id.
     * If id is not given, will create a new Role with a generated Id and specified values.
     * <p>
     * Mapped to the '/roles' route POST request
     *
     * @param role the Role object to be created/updated
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> saveRole(@RequestBody Role role) {
        Role r = null;
        if(role.getId() != null){
            r = roleRepository.findById(role.getId());
        }
        Set<User> requestUsers = role.getUsers();
        if (r != null) {
            Set<User> oldUsers = r.getUsers();

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

            for (User u : usersToAdd) {
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(r);
                u.setRoles(userRoles);
                newUsers.add(u);
                userRepository.save(u);
            }

            for (User u : usersToRemove) {
                u.setRoles(new HashSet<>());
                userRepository.save(u);
            }
            role.setUsers(newUsers);
        }
        roleRepository.save(role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Attempts to delete a Role from the RoleRepository.
     * id argument is an integer specifying the id of the Role you wish to
     * delete.
     * @param id the id of the Role to be deleted
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteRole(@PathVariable("id") Integer id){
        roleRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}