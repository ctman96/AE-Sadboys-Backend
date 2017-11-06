package com.ipfms.assembler;

import com.ipfms.domain.model.CustomAttribute;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAttributeResourceAssembler implements ResourceAssembler<CustomAttribute, Resource<CustomAttribute>> {
    @Override
    public Resource<CustomAttribute> toResource(CustomAttribute customAttribute) {
        Resource<CustomAttribute> resource = new Resource<>(customAttribute);
        resource.add(new Link("http://customattributes/" + customAttribute.getId()).withSelfRel());
        if (!(customAttribute.getLookup() == null)){
            resource.add(new Link ("http://customattributelookups/"+customAttribute.getLookup().getId(), "lookup"));
        }
        return resource;
    }
    public List<Resource<CustomAttribute>> toResources(List<CustomAttribute> customAttributes){
        List<Resource<CustomAttribute>> resources = new ArrayList<>();
        for(CustomAttribute c : customAttributes){
            resources.add(toResource(c));
        }
        return resources;
    }

}
