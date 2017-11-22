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
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller
 * <p>
 * Handles Requestmapping for the /labelcolours namespace
 */
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

    /**
     * Returns a ResponseEntity object containing a page of LabelColour objects, as HATEOAS PagedResources,
     * with the optionally specified page parameters. The optional size argument
     * specifies the page's size, must be an integer value. Defaults to '10'.
     * The optional page argument specifies the page number. Must be an integer,
     * Defaults to '0'
     * <p>
     * This is mapped to the '/labelcolours' route
     *
     * @param size  the size of pages you want returned (optional, default 10)
     * @param page  the page number, for the given size (optional, default 0)
     * @return      the ResponseEntity containing the page of LabelColour PagedResources
     */
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
        Page<LabelColour> pageResult = labelColourRepository.findByOrderByKeyAsc(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No LabelColours found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<LabelColour> resources = new PagedResources<>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showLabelColours'");
        return ResponseEntity.ok(resources);
    }

    /**
     * Returns a ResponseEntity containing all LabelColour Objects as HATEOAS Resources.
     * <p>
     * Mapped to the the '/labelcolours/all' route
     *
     * @return ResponseEntity containing all LabelColours, as Hateoas Resources
     */
    @RequestMapping("/all")
    public ResponseEntity<Resources<LabelColour>> showAllLabelColours() {
        System.out.println("In 'showAllLabelColours'");
        Iterable<LabelColour> labelcolours = labelColourRepository.findAll();
        if (labelcolours == null) {
            throw new EntityNotFoundException("No Roles found");
        }
        Resources<LabelColour> resources = new Resources<>(labelcolours);
        System.out.println("Exiting 'showAllLabelColours'");
        return ResponseEntity.ok(resources);
    }


    /**
     * Returns a Response Entity containing a LabelColour object, as a HATEOAS Resource,
     * with a key value matching the given key parameter. The key
     * argument corresponds to the 'key' PathVariable from the
     * RequestMapping, '/labelcolous/{key}'
     *
     * @param key   the key value of the LabelColour you are requesting
     * @return      Response Entity containing the corresponding LabelColour, as a HATEOAS Resource
     */
    @RequestMapping( value="/{key}", method = RequestMethod.GET)
    public ResponseEntity<Resource<LabelColour>> getLabelColour(@PathVariable("key") String key) throws EntityNotFoundException{
        LabelColour c = labelColourRepository.findByKey(key);
        if (c == null) {
            throw new EntityNotFoundException("LabelColour not found - key: " + key);
        }
        Resource<LabelColour> resource = labelColourResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    /**
     * Attempts to Create or Update a LabelColour in the LabelColourRepository.
     * labelColour argument is a LabelColour object.
     * The LabelColourRepository will attempt to save this object. If the given
     * key value is already in use, it will update the corresponding LabelColour.
     * Otherwise creates a new LabelColour with given values.
     * <p>
     * Mapped to the '/labelcolours' route POST request
     *
     * @param labelColour the LabelColour object to be created/updated
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createLabelColour(@RequestBody LabelColour labelColour) {
        labelColourRepository.save(labelColour);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Attempts to delete a LabelColour from the LabelColourRepository.
     * key argument is a string specifying the key of the LabelColour you wish to
     * delete.
     * @param key the key of the LabelColour to be deleted
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(value="/{key}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLabelColour(@PathVariable("key") String key){
        LabelColour c = labelColourRepository.findByKey(key);
        labelColourRepository.delete(c);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}