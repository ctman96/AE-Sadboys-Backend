package com.ipfms.controllers;

import com.ipfms.assembler.CustomAttributeValueResourceAssembler;
import com.ipfms.domain.model.CustomAttributeValue;
import com.ipfms.domain.repository.CustomAttributeValueRepository;
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
@RequestMapping("/customattributevalues")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomAttributeValueController{

    private final CustomAttributeValueRepository customAttributeValueRepository;
    private final CustomAttributeValueResourceAssembler customAttributeValueResourceAssembler;

    @Autowired
    public CustomAttributeValueController(CustomAttributeValueResourceAssembler resourceAssembler, CustomAttributeValueRepository repository){
        this.customAttributeValueRepository = repository;
        this.customAttributeValueResourceAssembler = resourceAssembler;
    }

    @RequestMapping()
    public ResponseEntity<List<Resource<CustomAttributeValue>>> showCustomAttributeValues() {
        List<CustomAttributeValue> c = (ArrayList<CustomAttributeValue>) customAttributeValueRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No CustomAttributeValues found");
        }
        List<Resource<CustomAttributeValue>> resources = customAttributeValueResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping( value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<CustomAttributeValue>> getCustomAttributeValue(@PathVariable("id") Integer id){
        CustomAttributeValue c = customAttributeValueRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("CustomAttributeValue not found - id: " + id);
        }
        Resource<CustomAttributeValue> resource = customAttributeValueResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createCustomAttributeValue(@RequestBody CustomAttributeValue customAttributeValue) {
        customAttributeValueRepository.save(customAttributeValue);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCustomAttributeValue(@PathVariable("id") Integer id){
        customAttributeValueRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}