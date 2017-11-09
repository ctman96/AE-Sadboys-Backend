package com.ipfms.controllers;

import com.ipfms.assembler.CustomAttributeResourceAssembler;
import com.ipfms.domain.model.CustomAttribute;
import com.ipfms.domain.repository.CustomAttributeRepository;
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
@RequestMapping("/customattributes")
public class CustomAttributeController{

    private final CustomAttributeRepository customAttributeRepository;
    private final CustomAttributeResourceAssembler customAttributeResourceAssembler;

    @Autowired
    public CustomAttributeController(CustomAttributeResourceAssembler resourceAssembler, CustomAttributeRepository repository){
        this.customAttributeRepository = repository;
        this.customAttributeResourceAssembler = resourceAssembler;
    }

    @RequestMapping()
    public ResponseEntity<List<Resource<CustomAttribute>>> showCustomAttributes() {
        List<CustomAttribute> c = (ArrayList<CustomAttribute>) customAttributeRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No CustomAttributes found");
        }
        List<Resource<CustomAttribute>> resources = customAttributeResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping( value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<CustomAttribute>> getCustomAttribute(@PathVariable("id") Integer id){
        CustomAttribute c = customAttributeRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("CustomAttribute not found - id: " + id);
        }
        Resource<CustomAttribute> resource = customAttributeResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createCustomAttribute(@RequestBody CustomAttribute customAttribute) {
        customAttributeRepository.save(customAttribute);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCustomAttribute(@PathVariable("id") Integer id){
        customAttributeRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}