package com.ipfms.controllers;

import com.ipfms.assembler.ClassHierarchyResourceAssembler;
import com.ipfms.domain.model.ClassHierarchy;
import com.ipfms.domain.repository.ClassHierarchyRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/classhierarchies")
public class ClassHierarchyController{

    private final ClassHierarchyRepository classHierarchyRepository;
    private final ClassHierarchyResourceAssembler classHierarchyResourceAssembler;

    @Autowired
    public ClassHierarchyController(ClassHierarchyResourceAssembler resourceAssembler, ClassHierarchyRepository repository){
        this.classHierarchyRepository = repository;
        this.classHierarchyResourceAssembler = resourceAssembler;
    }

    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Resource<ClassHierarchy>>> showClassHierarchies() {
        List<ClassHierarchy> c = (ArrayList<ClassHierarchy>) classHierarchyRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No ClassHierarchies found");
        }
        List<Resource<ClassHierarchy>> resources = classHierarchyResourceAssembler.toResources(c);
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(produces = APPLICATION_JSON_VALUE, value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<ClassHierarchy>> getClassHierarchy(@PathVariable("id") Integer id){
        ClassHierarchy c = classHierarchyRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("ClassHierarchy not found - id: " + id);
        }
        Resource<ClassHierarchy> resource = classHierarchyResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createClassHierarchy(@RequestBody ClassHierarchy classHierarchy) {
        classHierarchyRepository.save(classHierarchy);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteClassHierarchy(@PathVariable("id") Integer id){
        classHierarchyRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}