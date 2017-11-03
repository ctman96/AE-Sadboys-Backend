package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@Table(name = "customattributes")
@JsonApiResource(type = "customAttributes")
public class CustomAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private Integer id;
    private String name;
    private Integer lookupId;

    public CustomAttribute() {
        super();
    }

    public CustomAttribute(Integer id, String name, Integer lookupId) {
        this.id = id;
        this.name = name;
        this.lookupId = lookupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLookupId() {
        return lookupId;
    }

    public void setLookupId(Integer lookupId) {
        this.lookupId = lookupId;
    }
}
