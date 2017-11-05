package com.ipfms.controllers;

import com.ipfms.assembler.CustomAttributeValueResourceAssembler;
import com.ipfms.domain.model.CustomAttributeValue;
import com.ipfms.domain.repository.CustomAttributeValueRepository;
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
@RequestMapping("/customattributevalues")
public class CustomAttributeValueController{

    private final CustomAttributeValueRepository customAttributeValueRepository;
    private final CustomAttributeValueResourceAssembler customAttributeValueResourceAssembler;

    @Autowired
    public CustomAttributeValueController(CustomAttributeValueResourceAssembler resourceAssembler, CustomAttributeValueRepository repository){
        this.customAttributeValueRepository = repository;
        this.customAttributeValueResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<CustomAttributeValue> showCustomAttributeValues() {
        return (ArrayList<CustomAttributeValue>)customAttributeValueRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<CustomAttributeValue>> getCustomAttributeValue(@PathVariable("id") Integer id){
        CustomAttributeValue c = customAttributeValueRepository.findById(id);
        Resource<CustomAttributeValue> resource = customAttributeValueResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
