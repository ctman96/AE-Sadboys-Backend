package com.ipfms.assembler;

import com.ipfms.domain.model.RecordType;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class RecordTypeResourceAssembler implements ResourceAssembler<RecordType, Resource<RecordType>> {
    @Override
    public Resource<RecordType> toResource(RecordType recordType) {
        Resource<RecordType> resource = new Resource<>(recordType);
        resource.add(new Link("http://recordTypes/" + recordType.getId()).withSelfRel());
        return resource;
    }
}
