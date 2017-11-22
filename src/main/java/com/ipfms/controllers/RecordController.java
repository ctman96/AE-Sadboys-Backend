package com.ipfms.controllers;

import com.ipfms.assembler.RecordResourceAssembler;
import com.ipfms.domain.model.Record;
import com.ipfms.domain.repository.RecordRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller -
 * Handles RequestMapping for the '/records' namespace
 */
@RestController
@RequestMapping("/records")
public class RecordController{

    private final RecordRepository recordRepository;
    private final RecordResourceAssembler recordResourceAssembler;

    @Autowired
    public RecordController(RecordResourceAssembler resourceAssembler, RecordRepository repository){
        this.recordRepository = repository;
        this.recordResourceAssembler = resourceAssembler;
    }

    /**
     * Returns a ResponseEntity object containing a page of Record, as HATEOAS PagedResources,
     * with the optionally specified page parameters. The optional size argument
     * specifies the page's size, must be an integer value. Defaults to '10'.
     * The optional page argument specifies the page number. Must be an integer,
     * Defaults to '0'
     * <p>
     * This is mapped to the '/records' route
     *
     * @param size  the size of pages you want returned (optional, default 10)
     * @param page  the page number, for the given size (optional, default 0)
     * @return      the ResponseEntity containing the page of Record Hateoas Resources
     */
    @RequestMapping()
    public ResponseEntity<PagedResources<Record>> showRecords(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showRecords'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Record> pageResult = recordRepository.findAll(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Records found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<Record> resources = new PagedResources<Record>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showRecords'");
        return ResponseEntity.ok(resources);
    }

    /**
     * Returns a Response Entity containing a Record object, as a HATEOAS Resource,
     * with an id value matching the given id parameter. The id
     * argument corresponds to the 'id' PathVariable from the
     * RequestMapping, '/records/{id}'
     *
     * @param id   the id value of the Record you are requesting
     * @return      Response Entity containing the corresponding Record, as a HATEOAS Resource
     */
    @RequestMapping( value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Record>> getRecord(@PathVariable("id") Integer id){
        Record c = recordRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Record not found - id: " + id);
        }
        Resource<Record> resource = recordResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    /**
     * Gets the total number of containers.
     * @return the total count of Records from the datasource.
     */
    @RequestMapping(value="/count", method = RequestMethod.GET)
    ResponseEntity<Resource<Long>> getRecordCount(){
        Resource<Long> resource = new Resource<>(recordRepository.count());
        resource.add(new Link("http://records/count", "self"));
        return ResponseEntity.ok(resource);
    }
}