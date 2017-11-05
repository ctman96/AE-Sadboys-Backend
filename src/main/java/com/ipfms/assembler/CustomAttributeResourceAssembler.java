package com.ipfms.assembler;

import com.ipfms.domain.model.CustomAttribute;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAttributeResourceAssembler implements ResourceAssembler<CustomAttribute, Resource<CustomAttribute>> {
    @Override
    public Resource<CustomAttribute> toResource(CustomAttribute customAttribute) {
        Resource<CustomAttribute> resource = new Resource<>(customAttribute);
        resource.add(new Link("http://customattributes/" + customAttribute.getId()).withSelfRel());
        return resource;
    }
    public Resources<CustomAttribute> toResources(List<CustomAttribute> customAttributes){
        Resources<CustomAttribute> resources = new Resources<>(customAttributes);
        resources.add(new Link("http://customattributes/"));
        return resources;
    }

}
