package com.ipfms.assembler;

import com.ipfms.domain.model.Record;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordResourceAssembler implements ResourceAssembler<Record, Resource<Record>> {
    @Override
    public Resource<Record> toResource(Record record) {
        Resource<Record> resource = new Resource<>(record);
        resource.add(new Link("http://records/" + record.getId()).withSelfRel());
        return resource;
    }
    public Resources<Record> toResources(List<Record> records){
        Resources<Record> resources = new Resources<>(records);
        resources.add(new Link("http://records/"));
        return resources;
    }

}
