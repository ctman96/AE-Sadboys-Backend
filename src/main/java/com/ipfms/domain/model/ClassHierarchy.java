package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "classificationHierarchys")
public class ClassHierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private long id;
    private long parentId;
    private int rel;
    private long childId;

    public ClassHierarchy() {
        super();
    }

    public ClassHierarchy(long id, long parentId, int rel, long childId) {
        this.id = id;
        this.parentId = parentId;
        this.rel = rel;
        this.childId = childId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getRel() {
        return rel;
    }

    public void setRel(int rel) {
        this.rel = rel;
    }

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }
}
