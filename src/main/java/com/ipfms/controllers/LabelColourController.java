package com.ipfms.controllers;

import com.ipfms.assembler.LabelColourResourceAssembler;
import com.ipfms.domain.model.LabelColour;
import com.ipfms.domain.repository.LabelColourRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping()
    public ResponseEntity<PagedResources<LabelColour>> showLabelColours(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showLabelColours'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<LabelColour> pageResult = labelColourRepository.findAll(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No LabelColours found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<LabelColour> resources = new PagedResources<LabelColour>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showLabelColours'");
        return ResponseEntity.ok(resources);
    }


    @RequestMapping( value="/{key}", method = RequestMethod.GET)
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