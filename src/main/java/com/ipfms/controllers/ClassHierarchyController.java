package com.ipfms.controllers;

import com.ipfms.assembler.ClassHierarchyResourceAssembler;
import com.ipfms.domain.model.ClassHierarchy;
import com.ipfms.domain.model.Classification;
import com.ipfms.domain.repository.ClassHierarchyRepository;
import com.ipfms.domain.repository.ClassificationRepository;
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
    private final ClassificationRepository classificationRepository;

    @Autowired
    public ClassHierarchyController(ClassHierarchyResourceAssembler resourceAssembler, ClassHierarchyRepository repository, ClassificationRepository classRepository){
        this.classHierarchyRepository = repository;
        this.classHierarchyResourceAssembler = resourceAssembler;
        this.classificationRepository = classRepository;
    }

    @RequestMapping()
    public ResponseEntity<List<Resource<ClassHierarchy>>> showClassHierarchies() {
        System.out.println("In 'showClassHierarchies'");
        List<ClassHierarchy> c = (ArrayList<ClassHierarchy>) classHierarchyRepository.findAll();
        if (c == null) {
            throw new EntityNotFoundException("No ClassHierarchies found");
        }
        List<Resource<ClassHierarchy>> resources = classHierarchyResourceAssembler.toResources(c);
        System.out.println("Exiting 'showClassHierarchies'");
        return ResponseEntity.ok(resources);
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<ClassHierarchy>> getClassHierarchy(@PathVariable("id") Integer id){
        System.out.println("In 'getClassHierarchy'");
        ClassHierarchy c = classHierarchyRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("ClassHierarchy not found - id: " + id);
        }
        Resource<ClassHierarchy> resource = classHierarchyResourceAssembler.toResource(c);
        System.out.println("Exiting 'getClassHierarchy'");
        return ResponseEntity.ok(resource);
    }

    //Handles Create and Update. If the Request body contains an Id, it will update that hierarchy,
    //Otherwise, saves the new hierarchy with a generated Id
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createClassHierarchy(@RequestBody ClassHierarchy classHierarchy) {
        System.out.println("In 'createClassHierarchy'");
        Integer parentId = classHierarchy.getParent().getId();
        Integer childId = classHierarchy.getChild().getId();
        Integer rel = classHierarchy.getRel();
        classHierarchy.setParent(classificationRepository.findById(parentId));
        classHierarchy.setChild(classificationRepository.findById(childId));
        classHierarchyRepository.save(classHierarchy);
        System.out.println("Exiting 'createClassHierarchy'");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteClassHierarchy(@PathVariable("id") Integer id){
        System.out.println("In 'deleteClassHierarchy'");
        classHierarchyRepository.delete(id);
        System.out.println("Exiting 'deleteClassHierarchy'");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}