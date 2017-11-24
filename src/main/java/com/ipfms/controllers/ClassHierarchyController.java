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

/**
 * Rest Controller -
 * Handles RequestMapping for the '/classhierarchies' namespace
 */
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

    /**
     * Returns a ResponseEntity object containing a page of ClassHierarchy, as HATEOAS PagedResources,
     * with the optionally specified page parameters. The optional size argument
     * specifies the page's size, must be an integer value. Defaults to '10'.
     * The optional page argument specifies the page number. Must be an integer,
     * Defaults to '0'
     * <p>
     * This is mapped to the '/classhierarchies' route
     *
     * @param size  the size of pages you want returned (optional, default 10)
     * @param page  the page number, for the given size (optional, default 0)
     * @return      the ResponseEntity containing the page of ClassHierarchy Hateoas Resources
     */
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
        Page<ClassHierarchy> pageResult = classHierarchyRepository.findByOrderByParentAsc(pageable);
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


    /**
     * Returns a Response Entity containing a ClassHierarchy object, as a HATEOAS Resource,
     * with an id value matching the given id parameter. The id
     * argument corresponds to the 'id' PathVariable from the
     * RequestMapping, '/classhierarchies/{id}'
     *
     * @param id   the id value of the ClassHierarchy you are requesting
     * @return      Response Entity containing the corresponding ClassHierarchy, as a HATEOAS Resource
     */
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<ClassHierarchy>> getClassHierarchy(@PathVariable("id") Integer id){
        System.out.println("In 'getClassHierarchy'");
        ClassHierarchy c = classHierarchyRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("ClassHierarchy not found - id: " + id);
        }
        Resource<ClassHierarchy> resource = classHierarchyResourceAssembler.toResource(c);
        System.out.println("Exiting 'getClassHierarchy'");
        return ResponseEntity.ok(resource);
    }

    /**
     * Attempts to Create or Update a ClassHierarchy in the ClassHierarchyRepository.
     * classHierarchy argument is a ClassHierarchy object with an optional id field.
     * The ClassHierarchyRepository will attempt to save this object. If given an
     * id value, it will attempt to update the ClassHierarchy with corresponding id.
     * If id is not given, will create a new ClassHierarchy with a generated Id and specified values.
     * <p>
     * Mapped to the '/classhierarchies' route POST request
     *
     * @param classHierarchy the ClassHierarchy object to be created/updated
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createClassHierarchy(@RequestBody ClassHierarchy classHierarchy) {
        System.out.println("In 'createClassHierarchy'");

        Integer parentId = classHierarchy.getParent().getId();
        Integer childId = classHierarchy.getChild().getId();
        String parentName = classHierarchy.getParent().getName();
        String childName = classHierarchy.getChild().getName();
        if (parentId != null && childId != null){
            classHierarchy.setParent(classificationRepository.findById(parentId));
            classHierarchy.setChild(classificationRepository.findById(childId));
        }
        else if (parentName != null && childName != null){
            classHierarchy.setParent(classificationRepository.findByName(parentName));
            classHierarchy.setChild(classificationRepository.findByName(childName));
        }


        classHierarchyRepository.save(classHierarchy);
        System.out.println("Exiting 'createClassHierarchy'");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Attempts to delete a ClassHierarchy from the ClassHierarchyRepository.
     * id argument is an integer specifying the id of the ClassHierarchy you wish to
     * delete.
     * @param id the id of the ClassHierarchy to be deleted
     * @return a void ResponseEntity with a NO_CONTENT status
     */
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClassHierarchy(@PathVariable("id") Integer id){
        System.out.println("In 'deleteClassHierarchy'");
        classHierarchyRepository.delete(id);
        System.out.println("Exiting 'deleteClassHierarchy'");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}