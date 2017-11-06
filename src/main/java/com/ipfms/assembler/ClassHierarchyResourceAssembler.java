package com.ipfms.assembler;

import com.ipfms.domain.model.ClassHierarchy;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassHierarchyResourceAssembler implements ResourceAssembler<ClassHierarchy, Resource<ClassHierarchy>> {
    @Override
    public Resource<ClassHierarchy> toResource(ClassHierarchy classHierarchy) {
        Resource<ClassHierarchy> resource = new Resource<>(classHierarchy);
        resource.add(new Link("http://classhierarchies/" + classHierarchy.getId()).withSelfRel());
        resource.add(new Link("http://classifications/" + classHierarchy.getParent().getId(), "parent"));
        resource.add(new Link("http://classifications/" + classHierarchy.getChild().getId(), "child"));
        return resource;
    }
    public List<Resource<ClassHierarchy>> toResources(List<ClassHierarchy> classHierarchies){
        List<Resource<ClassHierarchy>> resources = new ArrayList<>();
        for(ClassHierarchy c : classHierarchies){
            resources.add(toResource(c));
        }
        return resources;
    }
}
