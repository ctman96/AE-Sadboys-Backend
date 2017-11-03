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
    private Integer id;
    private Integer parentId;
    private int rel;
    private Integer childId;

    public ClassHierarchy() {
        super();
    }

    public ClassHierarchy(Integer id, Integer parentId, int rel, Integer childId) {
        this.id = id;
        this.parentId = parentId;
        this.rel = rel;
        this.childId = childId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getRel() {
        return rel;
    }

    public void setRel(int rel) {
        this.rel = rel;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }
}
