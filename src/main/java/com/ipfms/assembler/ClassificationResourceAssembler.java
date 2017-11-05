package com.ipfms.assembler;

import com.ipfms.domain.model.Classification;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassificationResourceAssembler implements ResourceAssembler<Classification, Resource<Classification>> {
    @Override
    public Resource<Classification> toResource(Classification classification) {
        Resource<Classification> resource = new Resource<>(classification);
        resource.add(new Link("http://classifications/" + classification.getId()).withSelfRel());
        return resource;
    }
    public Resources<Classification> toResources(List<Classification> classifications){
        Resources<Classification> resources = new Resources<>(classifications);
        resources.add(new Link("http://classifications/"));
        return resources;
    }

}
