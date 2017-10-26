package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "classificationHierarchy")
public class ClassHierarchy {
    @JsonApiId
    private int id;
    private int parentId;
    private int rel;
    private int childId;

    public ClassHierarchy() {
        super();
    }

    public ClassHierarchy(int id, int parentId, int rel, int childId) {
        this.id = id;
        this.parentId = parentId;
        this.rel = rel;
        this.childId = childId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getRel() {
        return rel;
    }

    public void setRel(int rel) {
        this.rel = rel;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }
}
