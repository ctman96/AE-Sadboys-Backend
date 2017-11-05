package com.ipfms.assembler;

import com.ipfms.domain.model.ClassHierarchy;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassHierarchyResourceAssembler implements ResourceAssembler<ClassHierarchy, Resource<ClassHierarchy>> {
    @Override
    public Resource<ClassHierarchy> toResource(ClassHierarchy classHierarchy) {
        Resource<ClassHierarchy> resource = new Resource<>(classHierarchy);
        resource.add(new Link("http://classhierarchies/" + classHierarchy.getId()).withSelfRel());
        return resource;
    }
    public Resources<ClassHierarchy> toResources(List<ClassHierarchy> classHierarchies){
        Resources<ClassHierarchy> resources = new Resources<>(classHierarchies);
        resources.add(new Link("http://classhierarchies/"));
        return resources;
    }

}
