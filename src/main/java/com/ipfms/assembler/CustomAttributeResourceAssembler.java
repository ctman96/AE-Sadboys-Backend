package com.ipfms.assembler;

import com.ipfms.domain.model.CustomAttribute;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class CustomAttributeResourceAssembler implements ResourceAssembler<CustomAttribute, Resource<CustomAttribute>> {
    @Override
    public Resource<CustomAttribute> toResource(CustomAttribute customAttribute) {
        Resource<CustomAttribute> resource = new Resource<>(customAttribute);
        resource.add(new Link("http://customAttributes/" + customAttribute.getId()).withSelfRel());
        return resource;
    }

}
