package com.ipfms.controllers;

import com.ipfms.assembler.RecordResourceAssembler;
import com.ipfms.domain.model.Record;
import com.ipfms.domain.repository.RecordRepository;
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
@RequestMapping("/records")
public class RecordController{

    private final RecordRepository recordRepository;
    private final RecordResourceAssembler recordResourceAssembler;

    @Autowired
    public RecordController(RecordResourceAssembler resourceAssembler, RecordRepository repository){
        this.recordRepository = repository;
        this.recordResourceAssembler = resourceAssembler;
    }

    @RequestMapping
    public List<Record> showRecords() {
        return (ArrayList<Record>)recordRepository.findAll();
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Record>> getRecord(@PathVariable("id") Integer id){
        Record c = recordRepository.findById(id);
        Resource<Record> resource = recordResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

}
