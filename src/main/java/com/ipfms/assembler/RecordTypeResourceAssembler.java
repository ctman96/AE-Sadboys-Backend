package com.ipfms.assembler;

import com.ipfms.domain.model.RecordType;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordTypeResourceAssembler implements ResourceAssembler<RecordType, Resource<RecordType>> {
    @Override
    public Resource<RecordType> toResource(RecordType recordType) {
        Resource<RecordType> resource = new Resource<>(recordType);
        resource.add(new Link("http://recordtypes/" + recordType.getId()).withSelfRel());
        return resource;
    }
    public Resources<RecordType> toResources(List<RecordType> recordTypes){
        Resources<RecordType> resources = new Resources<>(recordTypes);
        resources.add(new Link("http://recordtypes/"));
        return resources;
    }

}
