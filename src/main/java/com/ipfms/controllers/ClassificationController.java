package com.ipfms.controllers;

import com.ipfms.assembler.ClassificationResourceAssembler;
import com.ipfms.domain.model.Classification;
import com.ipfms.domain.repository.ClassificationRepository;
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
@RequestMapping("/classifications")
public class ClassificationController{

    private final ClassificationRepository classificationRepository;
    private final ClassificationResourceAssembler classificationResourceAssembler;

    @Autowired
    public ClassificationController(ClassificationResourceAssembler resourceAssembler, ClassificationRepository repository){
        this.classificationRepository = repository;
        this.classificationResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<Classification> showClassifications() {
        return (ArrayList<Classification>)classificationRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Classification>> getClassification(@PathVariable("id") Integer id){
        Classification c = classificationRepository.findById(id);
        Resource<Classification> resource = classificationResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
