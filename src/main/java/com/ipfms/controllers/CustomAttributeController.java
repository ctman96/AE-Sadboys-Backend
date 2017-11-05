package com.ipfms.controllers;

import com.ipfms.assembler.CustomAttributeResourceAssembler;
import com.ipfms.domain.model.CustomAttribute;
import com.ipfms.domain.repository.CustomAttributeRepository;
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
@RequestMapping("/customattributes")
public class CustomAttributeController{

    private final CustomAttributeRepository customAttributeRepository;
    private final CustomAttributeResourceAssembler customAttributeResourceAssembler;

    @Autowired
    public CustomAttributeController(CustomAttributeResourceAssembler resourceAssembler, CustomAttributeRepository repository){
        this.customAttributeRepository = repository;
        this.customAttributeResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<CustomAttribute> showCustomAttributes() {
        return (ArrayList<CustomAttribute>)customAttributeRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<CustomAttribute>> getCustomAttribute(@PathVariable("id") Integer id){
        CustomAttribute c = customAttributeRepository.findById(id);
        Resource<CustomAttribute> resource = customAttributeResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
