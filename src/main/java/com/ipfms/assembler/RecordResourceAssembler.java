package com.ipfms.assembler;

import com.ipfms.domain.model.Classification;
import com.ipfms.domain.model.Record;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordResourceAssembler implements ResourceAssembler<Record, Resource<Record>> {
    @Override
    public Resource<Record> toResource(Record record) {
        Resource<Record> resource = new Resource<>(record);
        resource.add(new Link("http://records/" + record.getId()).withSelfRel());
        if(!(record.getSchedule() == null)){
            resource.add(new Link("TODO"+record.getSchedule().getId(),"schedule"));
        }
        if(!(record.getType() == null)){
            resource.add(new Link("TODO"+record.getType().getId(), "type"));
        }
        if(!(record.getState() == null)){
            resource.add(new Link ("TODO"+record.getState().getId(), "state"));
        }
        if(!(record.getContainer() == null)){
            resource.add(new Link ("http://containers/"+record.getContainer().getId(), "container"));
        }
        if(!(record.getLocation() == null)){
            resource.add(new Link ("http://locations/"+record.getLocation().getId(), "location"));
        }
        if(!(record.getClassifications() == null)){
            for (Classification c : record.getClassifications()){
                resource.add(new Link("http://classifications/"+c.getId(), "classification"));
            }
        }
        return resource;
    }
    public List<Resource<Record>> toResources(List<Record> records){
        List<Resource<Record>> resources = new ArrayList<>();
        for(Record c : records){
            resources.add(toResource(c));
        }
        return resources;
    }

}
