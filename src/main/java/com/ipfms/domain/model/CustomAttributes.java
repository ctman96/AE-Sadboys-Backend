package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "customAttributes")
public class CustomAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private int id;
    private String name;
    private int lookupId;

    public CustomAttributes() {
        super();
    }

    public CustomAttributes(int id, String name, int lookupId) {
        this.id = id;
        this.name = name;
        this.lookupId = lookupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLookupId() {
        return lookupId;
    }

    public void setLookupId(int lookupId) {
        this.lookupId = lookupId;
    }
}
