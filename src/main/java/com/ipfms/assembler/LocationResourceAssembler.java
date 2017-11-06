package com.ipfms.assembler;

import com.ipfms.domain.model.Location;
import com.ipfms.domain.model.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationResourceAssembler implements ResourceAssembler<Location, Resource<Location>> {
    @Override
    public Resource<Location> toResource(Location location) {
        Resource<Location> resource = new Resource<>(location);
        resource.add(new Link("http://locations/" + location.getId()).withSelfRel());
        if (!(location.getUsers() == null)){
            for (User u : location.getUsers()){
                resource.add(new Link("http://users/"+u.getId(), "user"));
            }
        }
        return resource;
    }
    public List<Resource<Location>> toResources(List<Location> locations){
        List<Resource<Location>> resources = new ArrayList<>();
        for(Location c : locations){
            resources.add(toResource(c));
        }
        return resources;
    }

}
