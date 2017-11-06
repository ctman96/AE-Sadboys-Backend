package com.ipfms.assembler;

import com.ipfms.domain.model.CustomAttributeLookup;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAttributeLookupResourceAssembler implements ResourceAssembler<CustomAttributeLookup, Resource<CustomAttributeLookup>> {
    @Override
    public Resource<CustomAttributeLookup> toResource(CustomAttributeLookup customAttributeLookup) {
        Resource<CustomAttributeLookup> resource = new Resource<>(customAttributeLookup);
        resource.add(new Link("http://customattributelookups/" + customAttributeLookup.getId()).withSelfRel());
        return resource;
    }
    public List<Resource<CustomAttributeLookup>> toResources(List<CustomAttributeLookup> customAttributeLookups){
        List<Resource<CustomAttributeLookup>> resources = new ArrayList<>();
        for(CustomAttributeLookup c : customAttributeLookups){
            resources.add(toResource(c));
        }
        return resources;
    }

}
