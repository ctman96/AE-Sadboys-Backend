package com.ipfms.assembler;

import com.ipfms.domain.model.LabelColour;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LabelColourResourceAssembler implements ResourceAssembler<LabelColour, Resource<LabelColour>> {
    @Override
    public Resource<LabelColour> toResource(LabelColour labelColour) {
        Resource<LabelColour> resource = new Resource<>(labelColour);
        resource.add(new Link("http://labelcolours/" + labelColour.getKey()).withSelfRel());
        return resource;
    }
    public List<Resource<LabelColour>> toResources(List<LabelColour> labelColours){
        List<Resource<LabelColour>> resources = new ArrayList<>();
        for(LabelColour c : labelColours){
            resources.add(toResource(c));
        }
        return resources;
    }

}
