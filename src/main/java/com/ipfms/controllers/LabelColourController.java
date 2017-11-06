package com.ipfms.controllers;

import com.ipfms.assembler.LabelColourResourceAssembler;
import com.ipfms.domain.model.LabelColour;
import com.ipfms.domain.repository.LabelColourRepository;
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
@RequestMapping("/labelcolours")
public class LabelColourController{

    private final LabelColourRepository labelColourRepository;
    private final LabelColourResourceAssembler labelColourResourceAssembler;

    @Autowired
    public LabelColourController(LabelColourResourceAssembler resourceAssembler, LabelColourRepository repository){
        this.labelColourRepository = repository;
        this.labelColourResourceAssembler = resourceAssembler;
    }

    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Resource<LabelColour>>> showLabelColours() {
        List<LabelColour> c = (ArrayList<LabelColour>) labelColourRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No LabelColours found");
        }
        List<Resource<LabelColour>> resources = labelColourResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{key}", method = RequestMethod.GET)
    ResponseEntity<Resource<LabelColour>> getLabelColour(@PathVariable("key") String key){
        LabelColour c = labelColourRepository.findByKey(key);
        if (c == null) {
            throw new EntityNotFoundException("LabelColour not found - key: " + key);
        }
        Resource<LabelColour> resource = labelColourResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createLabelColour(@RequestBody LabelColour labelColour) {
        labelColourRepository.save(labelColour);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{key}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteLabelColour(@PathVariable("key") String key){
        LabelColour c = labelColourRepository.findByKey(key);
        labelColourRepository.delete(c);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}