package com.ipfms.assembler;

import com.ipfms.domain.model.CustomAttributeValue;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class CustomAttributeValueResourceAssembler implements ResourceAssembler<CustomAttributeValue, Resource<CustomAttributeValue>> {
    @Override
    public Resource<CustomAttributeValue> toResource(CustomAttributeValue customAttributeValue) {
        Resource<CustomAttributeValue> resource = new Resource<>(customAttributeValue);
        resource.add(new Link("http://customAttributeValues/" + customAttributeValue.getId()).withSelfRel());
        return resource;
    }

}
