package com.ipfms.controllers;

import com.ipfms.assembler.CustomAttributeLookupResourceAssembler;
import com.ipfms.domain.model.CustomAttributeLookup;
import com.ipfms.domain.repository.CustomAttributeLookupRepository;
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
@RequestMapping("/customattributelookups")
public class CustomAttributeLookupController{

    private final CustomAttributeLookupRepository customAttributeLookupRepository;
    private final CustomAttributeLookupResourceAssembler customAttributeLookupResourceAssembler;

    @Autowired
    public CustomAttributeLookupController(CustomAttributeLookupResourceAssembler resourceAssembler, CustomAttributeLookupRepository repository){
        this.customAttributeLookupRepository = repository;
        this.customAttributeLookupResourceAssembler = resourceAssembler;
    }

    @RequestMapping()
    public ResponseEntity<List<Resource<CustomAttributeLookup>>> showCustomAttributeLookups() {
        List<CustomAttributeLookup> c = (ArrayList<CustomAttributeLookup>) customAttributeLookupRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No CustomAttributeLookups found");
        }
        List<Resource<CustomAttributeLookup>> resources = customAttributeLookupResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping( value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<CustomAttributeLookup>> getCustomAttributeLookup(@PathVariable("id") Integer id){
        CustomAttributeLookup c = customAttributeLookupRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("CustomAttributeLookup not found - id: " + id);
        }
        Resource<CustomAttributeLookup> resource = customAttributeLookupResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createCustomAttributeLookup(@RequestBody CustomAttributeLookup customAttributeLookup) {
        customAttributeLookupRepository.save(customAttributeLookup);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCustomAttributeLookup(@PathVariable("id") Integer id){
        customAttributeLookupRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}