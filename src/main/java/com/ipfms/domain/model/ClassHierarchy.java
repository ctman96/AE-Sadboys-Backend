package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "classhierarchy")
public class ClassHierarchy {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="ParentId", insertable = false, updatable = false)
    @JsonIgnoreProperties({"parentHierarchies", "childHierarchies", "records"})
    private Classification parent;
    private int rel;

    @ManyToOne
    @JoinColumn(name="ChildId", insertable = false, updatable = false)
    @JsonIgnoreProperties({"parentHierarchies", "childHierarchies", "records"})
    private Classification child;

    public ClassHierarchy() {
        super();
    }

    public ClassHierarchy(Classification parent, int rel, Classification child) {
        this.parent = parent;
        this.rel = rel;
        this.child = child;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Classification getParent() {
        return parent;
    }

    public void setParent(Classification parent) {
        this.parent = parent;
    }

    public int getRel() {
        return rel;
    }

    public void setRel(int rel) {
        this.rel = rel;
    }

    public Classification getChild() {
        return child;
    }

    public void setChild(Classification child) {
        this.child = child;
    }
}
