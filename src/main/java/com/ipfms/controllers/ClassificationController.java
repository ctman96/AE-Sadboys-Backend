package com.ipfms.controllers;

import com.ipfms.assembler.ClassificationResourceAssembler;
import com.ipfms.domain.model.Classification;
import com.ipfms.domain.repository.ClassificationRepository;
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

import java.util.Date;


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

    @RequestMapping()
    ResponseEntity<PagedResources<Classification>> showClassifications(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showClassifications'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Classification> pageResult = classificationRepository.findAll(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Classifications found: Page="+page+", Size="+size);
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<Classification> resources = new PagedResources<Classification>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showClassifications'");
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Classification>> getClassification(@PathVariable("id") Integer id){
        System.out.println("In 'getClassification'");
        Classification c = classificationRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Classification not found - id: " + id);
        }
        Resource<Classification> resource = classificationResourceAssembler.toResource(c);
        System.out.println("Exiting 'getClassification'");
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createClassification(@RequestBody Classification classification) {
        System.out.println("In 'createClassification'");
        classification.setUpdatedAt(new Date());
        classificationRepository.save(classification);
        System.out.println("Exiting 'createClassification'");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteClassification(@PathVariable("id") Integer id){
        System.out.println("In 'deleteClassification'");
        classificationRepository.delete(id);
        System.out.println("Exiting 'deleteClassification'");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}