package com.ipfms.assembler;

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
public class RoleResourceAssembler implements ResourceAssembler<Role, Resource<Role>> {
    @Override
    public Resource<Role> toResource(Role role) {
        Resource<Role> resource = new Resource<>(role);
        resource.add(new Link("http://roles/" + role.getId()).withSelfRel());
        if (!(role.getUsers() == null)){
            for (User u : role.getUsers()){
                resource.add(new Link("http://users/"+u.getId(), "user"));
            }
        }
        return resource;
    }
    public List<Resource<Role>> toResources(List<Role> roles){
        List<Resource<Role>> resources = new ArrayList<>();
        for(Role c : roles){
            resources.add(toResource(c));
        }
        return resources;
    }

}
