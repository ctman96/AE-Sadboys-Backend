package com.ipfms.assembler;

import com.ipfms.domain.model.Classification;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassificationResourceAssembler implements ResourceAssembler<Classification, Resource<Classification>> {
    @Override
    public Resource<Classification> toResource(Classification classification) {
        Resource<Classification> resource = new Resource<>(classification);
        resource.add(new Link("http://classifications/" + classification.getId()).withSelfRel());
        return resource;
    }
    public List<Resource<Classification>> toResources(List<Classification> classifications){
        List<Resource<Classification>> resources = new ArrayList<>();
        for(Classification c : classifications){
            resources.add(toResource(c));
        }
        return resources;
    }

}
