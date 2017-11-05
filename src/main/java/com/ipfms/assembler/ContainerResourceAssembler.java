package com.ipfms.assembler;

import com.ipfms.domain.model.Container;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class ContainerResourceAssembler implements ResourceAssembler<Container, Resource<Container>> {
    @Override
    public Resource<Container> toResource(Container container) {
        Resource<Container> resource = new Resource<>(container);
        resource.add(new Link("http://containers/" + container.getId()).withSelfRel());
        return resource;
    }

}
