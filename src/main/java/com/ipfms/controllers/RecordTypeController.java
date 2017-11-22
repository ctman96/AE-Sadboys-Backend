package com.ipfms.controllers;

import com.ipfms.domain.model.RecordType;
import com.ipfms.domain.repository.RecordTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller -
 * Handles RequestMapping for the '/recordtypes' namespace
 */
@RestController
@RequestMapping("/recordtypes")
public class RecordTypeController {
    private final RecordTypeRepository recordTypeRepository;

    @Autowired
    public RecordTypeController(RecordTypeRepository recordTypeRepository){
        this.recordTypeRepository = recordTypeRepository;
    }

    /**
     * Returns a ResponseEntity containing all RecordType Objects as HATEOAS Resources.
     * <p>
     * Mapped to the the '/recordtypes' route
     *
     * @return ResponseEntity containing all RecordTypes, as Hateoas Resources
     */
    @RequestMapping()
    public ResponseEntity<Resources<RecordType>> getTypes(){
        System.out.println("In 'getTypes'");
        Iterable<RecordType> recordTypes = recordTypeRepository.findAll();
        Resources<RecordType> recordTypeResources = new Resources<RecordType>(recordTypes);
        System.out.println("Exiting 'getTypes'");
        return ResponseEntity.ok(recordTypeResources);
    }
}
