package com.ipfms.assembler;

import com.ipfms.domain.model.LabelColour;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class LabelColourResourceAssembler implements ResourceAssembler<LabelColour, Resource<LabelColour>> {
    @Override
    public Resource<LabelColour> toResource(LabelColour labelColour) {
        Resource<LabelColour> resource = new Resource<>(labelColour);
        resource.add(new Link("http://labelColours/" + labelColour.getKey()).withSelfRel());
        return resource;
    }
}
