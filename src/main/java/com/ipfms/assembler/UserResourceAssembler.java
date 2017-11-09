package com.ipfms.assembler;

import com.ipfms.domain.model.Location;
import com.ipfms.domain.model.Role;
import com.ipfms.domain.model.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {
    @Override
    public Resource<User> toResource(User user) {
        Resource<User> resource = new Resource<>(user);
        resource.add(new Link("http://users/" + user.getId()).withSelfRel());
        if(!(user.getRoles() == null)){
            for (Role r : user.getRoles()){
                resource.add(new Link("http://roles/"+r.getId(), "role"));
            }
        }
        if(!(user.getLocations() == null)){
            for (Location l : user.getLocations()){
                resource.add(new Link("http://locations/"+l.getId(), "location"));
            }
        }
        return resource;
    }
    public List<Resource<User>> toResources(List<User> users){
        List<Resource<User>> resources = new ArrayList<>();
        for(User c : users){
            resources.add(toResource(c));
        }
        return resources;
    }

}
