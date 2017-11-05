package com.ipfms.controllers;

import com.ipfms.domain.model.RecordClassification;
import com.ipfms.domain.repository.RecordClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recordClassifications")
public class RecordClassificationController{

    private final RecordClassificationRepository recordClassificationRepository;

    @Autowired
    public RecordClassificationController(RecordClassificationRepository repository){
        this.recordClassificationRepository = repository;
    }

    @RequestMapping
    public List<RecordClassification> showRecordClassifications() {
        return (ArrayList<RecordClassification>)recordClassificationRepository.findAll();
    }

    //TODO: Not sure how to handle this yet
    /*@RequestMapping(value="/{id}", method = RequestMethod.GET)
    RecordClassification getRecordClassification(@PathVariable("id") Integer id){
        return recordClassificationRepository.findById(id);
    }*/
}