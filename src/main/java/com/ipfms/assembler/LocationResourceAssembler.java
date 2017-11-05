package com.ipfms.assembler;

import com.ipfms.domain.model.Location;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationResourceAssembler implements ResourceAssembler<Location, Resource<Location>> {
    @Override
    public Resource<Location> toResource(Location location) {
        Resource<Location> resource = new Resource<>(location);
        resource.add(new Link("http://locations/" + location.getId()).withSelfRel());
        return resource;
    }
    public Resources<Location> toResources(List<Location> locations){
        Resources<Location> resources = new Resources<>(locations);
        resources.add(new Link("http://locations/"));
        return resources;
    }

}
