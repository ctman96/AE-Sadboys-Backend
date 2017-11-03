package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@Table(name = "labelcolours")
@JsonApiResource(type = "labelColours")
public class LabelColour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private String key;
    private String colour;

    public LabelColour() {
        super();
    }

    public LabelColour(String key, String colour) {
        this.key = key;
        this.colour = colour;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}