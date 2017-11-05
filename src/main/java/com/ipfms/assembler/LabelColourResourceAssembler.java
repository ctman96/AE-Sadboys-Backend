package com.ipfms.assembler;

import com.ipfms.domain.model.LabelColour;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LabelColourResourceAssembler implements ResourceAssembler<LabelColour, Resource<LabelColour>> {
    @Override
    public Resource<LabelColour> toResource(LabelColour labelColour) {
        Resource<LabelColour> resource = new Resource<>(labelColour);
        resource.add(new Link("http://labelcolours/" + labelColour.getKey()).withSelfRel());
        return resource;
    }
    public Resources<LabelColour> toResources(List<LabelColour> labelColours){
        Resources<LabelColour> resources = new Resources<>(labelColours);
        resources.add(new Link("http://labelcolours/"));
        return resources;
    }

}
