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

/**
 * Rest Controller
 * <p>
 * Handles RequestMapping for the /classifications namespace
 */
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

    /**
     * Returns a ResponseEntity object containing a page of Classification, as HATEOAS PagedResources,
     * with the optionally specified page parameters. The optional size argument
     * specifies the page's size, must be an integer value. Defaults to '10'.
     * The optional page argument specifies the page number. Must be an integer,
     * Defaults to '0'
     * <p>
     * This is mapped to the '/classifications' route
     *
     * @param size  the size of pages you want returned (optional, default 10)
     * @param page  the page number, for the given size (optional, default 0)
     * @return      the ResponseEntity containing the page of Classification Hateoas Resources
     */
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
        Page<Classification> pageResult = classificationRepository.findByOrderByNameAsc(pageable);
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


    /**
     * Returns a Response Entity containing a Classification object, as a HATEOAS Resource,
     * with an id value matching the given id parameter. The id
     * argument corresponds to the 'id' PathVariable from the
     * RequestMapping, '/classifications/{id}'
     *
     * @param id   the id value of the Classification you are requesting
     * @return      Response Entity containing the corresponding Classification, as a HATEOAS Resource
     */
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

    /**
     * Attempts to Create or Update a Classification in the ClassificationRepository.
     * classification argument is a Classification object with an optional id field.
     * The ClassificationRepository will attempt to save this object. If given an
     * id value, it will attempt to update the Classification with corresponding id.
     * If id is not given, will create a new Classification with a generated Id and specified values.
     * <p>
     * Mapped to the '/classifications' route POST request
     *
     * @param classification the Classification object to be created/updated
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createClassification(@RequestBody Classification classification) {
        System.out.println("In 'createClassification'");
        classification.setUpdatedAt(new Date());
        classificationRepository.save(classification);
        System.out.println("Exiting 'createClassification'");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Attempts to delete a Classification from the ClassificationRepository.
     * id argument is an integer specifying the id of the Classification you wish to
     * delete.
     * @param id the id of the Classification to be deleted
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteClassification(@PathVariable("id") Integer id){
        System.out.println("In 'deleteClassification'");
        classificationRepository.delete(id);
        System.out.println("Exiting 'deleteClassification'");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}