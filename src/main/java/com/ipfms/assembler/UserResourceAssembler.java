package com.ipfms.assembler;

import com.ipfms.domain.model.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {
    @Override
    public Resource<User> toResource(User user) {
        Resource<User> resource = new Resource<>(user);
        resource.add(new Link("http://users/" + user.getId()).withSelfRel());
        return resource;
    }

}
