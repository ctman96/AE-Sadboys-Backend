package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@JsonApiResource(type = "labelColors")
public class LabelColor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private String key;
    private String color;

    public LabelColor() {
        super();
    }

    public LabelColor(String key, String color) {
        this.key = key;
        this.color = color;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}