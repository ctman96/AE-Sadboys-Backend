package com.ipfms.controllers;

import com.ipfms.domain.model.RetentionSchedule;
import com.ipfms.domain.repository.RetentionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller -
 * Handles RequestMapping for the '/retentionschedules' namespace
 */
@RestController
@RequestMapping("/retentionschedules")
public class RetentionScheduleController {
    private final RetentionScheduleRepository retentionScheduleRepository;

    @Autowired
    public RetentionScheduleController(RetentionScheduleRepository retentionScheduleRepository){
        this.retentionScheduleRepository = retentionScheduleRepository;
    }

    /**
     * Returns a ResponseEntity containing all RetentionSchedule Objects as HATEOAS Resources.
     * <p>
     * Mapped to the the '/retentionschedules' route
     *
     * @return ResponseEntity containing all RetentionSchedules, as Hateoas Resources
     */
    @RequestMapping()
    public ResponseEntity<Resources<RetentionSchedule>> getSchedules(){
        System.out.println("In 'getSchedules'");
        Iterable<RetentionSchedule> retentionSchedules = retentionScheduleRepository.findAll();
        Resources<RetentionSchedule> retentionScheduleResources = new Resources<RetentionSchedule>(retentionSchedules);
        System.out.println("Exiting 'getSchedules'");
        return ResponseEntity.ok(retentionScheduleResources);
    }
}
