package com.ipfms.assembler;

import com.ipfms.domain.model.CustomAttributeValue;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAttributeValueResourceAssembler implements ResourceAssembler<CustomAttributeValue, Resource<CustomAttributeValue>> {
    @Override
    public Resource<CustomAttributeValue> toResource(CustomAttributeValue customAttributeValue) {
        Resource<CustomAttributeValue> resource = new Resource<>(customAttributeValue);
        resource.add(new Link("http://customattributevalues/" + customAttributeValue.getId()).withSelfRel());
        if (!(customAttributeValue.getAttribute() == null)){
            resource.add(new Link ("http://customattributes/"+customAttributeValue.getAttribute().getId(),"attribute"));
        }
        if (!(customAttributeValue.getRecord() == null)){
            resource.add(new Link ("http://records/"+customAttributeValue.getRecord().getId(),"record"));
        }
        return resource;
    }
    public List<Resource<CustomAttributeValue>> toResources(List<CustomAttributeValue> customAttributeValues){
        List<Resource<CustomAttributeValue>> resources = new ArrayList<>();
        for(CustomAttributeValue c : customAttributeValues){
            resources.add(toResource(c));
        }
        return resources;
    }

}
