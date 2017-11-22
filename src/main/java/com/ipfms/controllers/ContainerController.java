package com.ipfms.controllers;

import com.ipfms.assembler.ContainerResourceAssembler;
import com.ipfms.domain.model.Container;
import com.ipfms.domain.repository.ContainerRepository;
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
 * Rest Controller
 * <p>
 * Handles RequestMapping for the /containers namespace
 */
@RestController
@RequestMapping("/containers")
public class ContainerController{

    private final ContainerRepository containerRepository;
    private final ContainerResourceAssembler containerResourceAssembler;

    @Autowired
    public ContainerController(ContainerResourceAssembler resourceAssembler, ContainerRepository repository){
        this.containerRepository = repository;
        this.containerResourceAssembler = resourceAssembler;
    }

    /**
     * Returns a ResponseEntity object containing a page of Container, as HATEOAS PagedResources,
     * with the optionally specified page parameters. The optional size argument
     * specifies the page's size, must be an integer value. Defaults to '10'.
     * The optional page argument specifies the page number. Must be an integer,
     * Defaults to '0'
     * <p>
     * This is mapped to the '/containers' route
     *
     * @param size  the size of pages you want returned (optional, default 10)
     * @param page  the page number, for the given size (optional, default 0)
     * @return      the ResponseEntity containing the page of Container Hateoas Resources
     */
    @RequestMapping()
    public ResponseEntity<PagedResources<Container>> showContainers(
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page) {
        System.out.println("In 'showContainers'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        Pageable pageable = new PageRequest(page, size);
        Page<Container> pageResult = containerRepository.findAll(pageable);
        if (pageResult == null) {
            throw new EntityNotFoundException("No Containers found");
        }
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());
        PagedResources<Container> resources = new PagedResources<Container>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showContainers'");
        return ResponseEntity.ok(resources);
    }

    /**
     * Returns a Response Entity containing a Container object, as a HATEOAS Resource,
     * with an id value matching the given id parameter. The id
     * argument corresponds to the 'id' PathVariable from the
     * RequestMapping, '/containers/{id}'
     *
     * @param id   the id value of the Container you are requesting
     * @return      Response Entity containing the corresponding Container, as a HATEOAS Resource
     */
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Container>> getContainer(@PathVariable("id") Integer id){
        Container c = containerRepository.findById(id);
        if (c == null) {
            throw new EntityNotFoundException("Container not found - id: " + id);
        }
        Resource<Container> resource = containerResourceAssembler.toResource(c);
        return ResponseEntity.ok(resource);
    }

    /**
     * Gets the total number of containers.
     * @return the total count of Containers from the datasource.
     */
    @RequestMapping(value="/count", method = RequestMethod.GET)
    ResponseEntity<Resource<Long>> getContainerCount(){
        Resource<Long> resource = new Resource<>(containerRepository.count());
        resource.add(new Link("http://containers/count", "self"));
        return ResponseEntity.ok(resource);
    }
}