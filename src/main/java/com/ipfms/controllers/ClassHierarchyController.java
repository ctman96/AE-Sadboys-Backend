package com.ipfms.controllers;

import com.ipfms.assembler.ClassHierarchyResourceAssembler;
import com.ipfms.domain.model.ClassHierarchy;
import com.ipfms.domain.repository.ClassHierarchyRepository;
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
    public ResponseEntity<PagedResources<ClassHierarchy>> showClassHierarchies(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showClassHierarchies'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<ClassHierarchy> pageResult = classHierarchyRepository.findAll(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No ClassHierarchies found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<ClassHierarchy> resources = new PagedResources<ClassHierarchy>(pageResult.getContent(), metadata);
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