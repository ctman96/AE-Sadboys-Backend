package com.ipfms.controllers;

import com.ipfms.assembler.ContainerResourceAssembler;
import com.ipfms.domain.model.Container;
import com.ipfms.domain.repository.ContainerRepository;
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
@RequestMapping("/containers")
public class ContainerController{

    private final ContainerRepository containerRepository;
    private final ContainerResourceAssembler containerResourceAssembler;

    @Autowired
    public ContainerController(ContainerResourceAssembler resourceAssembler, ContainerRepository repository){
        this.containerRepository = repository;
        this.containerResourceAssembler = resourceAssembler;
    }

    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Resource<Container>>> showContainers() {
        List<Container> c = (ArrayList<Container>) containerRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No Containers found");
        }
        List<Resource<Container>> resources = containerResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
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