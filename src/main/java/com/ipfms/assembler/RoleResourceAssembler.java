package com.ipfms.assembler;

import com.ipfms.domain.model.Role;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleResourceAssembler implements ResourceAssembler<Role, Resource<Role>> {
    @Override
    public Resource<Role> toResource(Role role) {
        Resource<Role> resource = new Resource<>(role);
        resource.add(new Link("http://roles/" + role.getId()).withSelfRel());
        return resource;
    }
    public Resources<Role> toResources(List<Role> roles){
        Resources<Role> resources = new Resources<>(roles);
        resources.add(new Link("http://roles/"));
        return resources;
    }

}
