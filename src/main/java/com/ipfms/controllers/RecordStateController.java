package com.ipfms.controllers;

import com.ipfms.domain.model.RecordState;
import com.ipfms.domain.repository.RecordStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/recordstates")
public class RecordStateController {
    private final RecordStateRepository recordStateRepository;

    @Autowired
    public RecordStateController(RecordStateRepository recordStateRepository){
        this.recordStateRepository = recordStateRepository;
    }

    @RequestMapping()
    public ResponseEntity<Resources<RecordState>> getStates(){
        System.out.println("In 'getStates'");
        Iterable<RecordState> recordStates = recordStateRepository.findAll();
        Resources<RecordState> recordStateResources = new Resources<RecordState>(recordStates);
        System.out.println("Exiting 'getStates'");
        return ResponseEntity.ok(recordStateResources);
    }
}
