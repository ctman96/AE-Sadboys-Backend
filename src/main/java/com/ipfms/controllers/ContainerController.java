package com.ipfms.controllers;

import com.ipfms.assembler.ContainerResourceAssembler;
import com.ipfms.domain.model.Container;
import com.ipfms.domain.repository.ContainerRepository;
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
@RequestMapping("/containers")
public class ContainerController{

    private final ContainerRepository containerRepository;
    private final ContainerResourceAssembler containerResourceAssembler;

    @Autowired
    public ContainerController(ContainerResourceAssembler resourceAssembler, ContainerRepository repository){
        this.containerRepository = repository;
        this.containerResourceAssembler = resourceAssembler;
    }

    @RequestMapping()
    public ResponseEntity<Page<Container>> showContainers(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showContainers'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Container> pageResult = containerRepository.findAll(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Containers found");
        }
        //TODO
        //List<Resource<Container>> resources = containerResourceAssembler.toResources(c);
        System.out.println("Exiting 'showContainers'");
        return ResponseEntity.ok(pageResult);
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Container>> getContainer(@PathVariable("id") Integer id){
        Container c = containerRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Container not found - id: " + id);
        }
        Resource<Container> resource = containerResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createContainer(@RequestBody Container container) {
        containerRepository.save(container);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteContainer(@PathVariable("id") Integer id){
        containerRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}