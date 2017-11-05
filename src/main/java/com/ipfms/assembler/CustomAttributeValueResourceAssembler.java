package com.ipfms.assembler;

import com.ipfms.domain.model.CustomAttributeValue;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAttributeValueResourceAssembler implements ResourceAssembler<CustomAttributeValue, Resource<CustomAttributeValue>> {
    @Override
    public Resource<CustomAttributeValue> toResource(CustomAttributeValue customAttributeValue) {
        Resource<CustomAttributeValue> resource = new Resource<>(customAttributeValue);
        resource.add(new Link("http://customattributevalues/" + customAttributeValue.getId()).withSelfRel());
        return resource;
    }
    public Resources<CustomAttributeValue> toResources(List<CustomAttributeValue> customAttributeValues){
        Resources<CustomAttributeValue> resources = new Resources<>(customAttributeValues);
        resources.add(new Link("http://customattributevalues/"));
        return resources;
    }

}
