package com.ipfms.controllers;

import com.ipfms.assembler.ClassificationResourceAssembler;
import com.ipfms.domain.model.Classification;
import com.ipfms.domain.repository.ClassificationRepository;
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
@RequestMapping("/classifications")
public class ClassificationController{

    private final ClassificationRepository classificationRepository;
    private final ClassificationResourceAssembler classificationResourceAssembler;

    @Autowired
    public ClassificationController(ClassificationResourceAssembler resourceAssembler, ClassificationRepository repository){
        this.classificationRepository = repository;
        this.classificationResourceAssembler = resourceAssembler;
    }

    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Classification>> showClassHierarchies() {
        List<Classification> c = (ArrayList<Classification>) classificationRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No Classifications found");
        }
        Resources<Classification> resources = classificationResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Classification>> getClassification(@PathVariable("id") Integer id){
        Classification c = classificationRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Classification not found - id: " + id);
        }
        Resource<Classification> resource = classificationResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createClassification(@RequestBody Classification classification) {
        classificationRepository.save(classification);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteClassification(@PathVariable("id") Integer id){
        classificationRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}