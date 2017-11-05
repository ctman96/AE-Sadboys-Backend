package com.ipfms.assembler;

import com.ipfms.domain.model.CustomAttributeLookup;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAttributeLookupResourceAssembler implements ResourceAssembler<CustomAttributeLookup, Resource<CustomAttributeLookup>> {
    @Override
    public Resource<CustomAttributeLookup> toResource(CustomAttributeLookup customAttributeLookup) {
        Resource<CustomAttributeLookup> resource = new Resource<>(customAttributeLookup);
        resource.add(new Link("http://customattributelookups/" + customAttributeLookup.getId()).withSelfRel());
        return resource;
    }
    public Resources<CustomAttributeLookup> toResources(List<CustomAttributeLookup> customAttributeLookups){
        Resources<CustomAttributeLookup> resources = new Resources<>(customAttributeLookups);
        resources.add(new Link("http://customattributelookups/"));
        return resources;
    }

}
