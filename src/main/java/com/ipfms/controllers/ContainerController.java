package com.ipfms.controllers;

import com.ipfms.assembler.ContainerResourceAssembler;
import com.ipfms.domain.model.Container;
import com.ipfms.domain.repository.ContainerRepository;
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
@RequestMapping("/containers")
public class ContainerController{

    private final ContainerRepository containerRepository;
    private final ContainerResourceAssembler containerResourceAssembler;

    @Autowired
    public ContainerController(ContainerResourceAssembler resourceAssembler, ContainerRepository repository){
        this.containerRepository = repository;
        this.containerResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<Container> showContainers() {
        return (ArrayList<Container>)containerRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Container>> getContainer(@PathVariable("id") Integer id){
        Container c = containerRepository.findById(id);
        Resource<Container> resource = containerResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
