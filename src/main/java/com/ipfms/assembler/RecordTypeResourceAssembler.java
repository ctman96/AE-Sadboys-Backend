package com.ipfms.assembler;

import com.ipfms.domain.model.RecordType;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordTypeResourceAssembler implements ResourceAssembler<RecordType, Resource<RecordType>> {
    @Override
    public Resource<RecordType> toResource(RecordType recordType) {
        Resource<RecordType> resource = new Resource<>(recordType);
        resource.add(new Link("http://recordtypes/" + recordType.getId()).withSelfRel());
        if(!(recordType.getDefaultSchedule() == null)){
            resource.add(new Link("http://TODO"+recordType.getDefaultSchedule().getId(), "schedule"));
        }
        return resource;
    }
    public List<Resource<RecordType>> toResources(List<RecordType> recordTypes){
        List<Resource<RecordType>> resources = new ArrayList<>();
        for(RecordType c : recordTypes){
            resources.add(toResource(c));
        }
        return resources;
    }

}
