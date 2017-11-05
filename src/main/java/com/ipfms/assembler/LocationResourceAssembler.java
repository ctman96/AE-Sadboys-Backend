package com.ipfms.assembler;

import com.ipfms.domain.model.Location;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class LocationResourceAssembler implements ResourceAssembler<Location, Resource<Location>> {
    @Override
    public Resource<Location> toResource(Location location) {
        Resource<Location> resource = new Resource<>(location);
        resource.add(new Link("http://locations/" + location.getId()).withSelfRel());
        return resource;
    }

}