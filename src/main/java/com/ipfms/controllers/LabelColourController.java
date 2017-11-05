package com.ipfms.controllers;

import com.ipfms.assembler.LabelColourResourceAssembler;
import com.ipfms.domain.model.LabelColour;
import com.ipfms.domain.repository.LabelColourRepository;
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
@RequestMapping("/labelcolours")
public class LabelColourController{

    private final LabelColourRepository labelColourRepository;
    private final LabelColourResourceAssembler labelColourResourceAssembler;

    @Autowired
    public LabelColourController(LabelColourResourceAssembler resourceAssembler, LabelColourRepository repository){
        this.labelColourRepository = repository;
        this.labelColourResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<LabelColour> showLabelColours() {
        return (ArrayList<LabelColour>)labelColourRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{key}", method = RequestMethod.GET)
    ResponseEntity<Resource<LabelColour>> getLabelColour(@PathVariable("key") String key){
        LabelColour c = labelColourRepository.findByKey(key);
        Resource<LabelColour> resource = labelColourResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
