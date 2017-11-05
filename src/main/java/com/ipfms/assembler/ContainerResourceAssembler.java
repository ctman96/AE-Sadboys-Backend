package com.ipfms.assembler;

import com.ipfms.domain.model.Container;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContainerResourceAssembler implements ResourceAssembler<Container, Resource<Container>> {
    @Override
    public Resource<Container> toResource(Container container) {
        Resource<Container> resource = new Resource<>(container);
        resource.add(new Link("http://containers/" + container.getId()).withSelfRel());
        return resource;
    }
    public Resources<Container> toResources(List<Container> containers){
        Resources<Container> resources = new Resources<>(containers);
        resources.add(new Link("http://containers/"));
        return resources;
    }

}
