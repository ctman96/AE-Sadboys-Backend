package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "customAttributes")
public class CustomAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private long id;
    private String name;
    private long lookupId;

    public CustomAttribute() {
        super();
    }

    public CustomAttribute(long id, String name, long lookupId) {
        this.id = id;
        this.name = name;
        this.lookupId = lookupId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLookupId() {
        return lookupId;
    }

    public void setLookupId(long lookupId) {
        this.lookupId = lookupId;
    }
}
