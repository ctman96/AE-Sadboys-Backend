package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String keyword;
    @Column(name="UpdatedAt")
    private Date updatedAt;

    @JsonIgnore
    @OneToMany(targetEntity=ClassHierarchy.class, mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<ClassHierarchy> parentHierarchies;

    @JsonIgnore
    @OneToMany(targetEntity=ClassHierarchy.class, mappedBy="child", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<ClassHierarchy> childHierarchies;

    @JsonIgnore
    @ManyToMany(mappedBy = "classifications")
    private Set<Record> records;

    public Classification() {
        super();
    }

    public Classification(String name, String keyword, Date updatedAt) {
        this.name = name;
        this.keyword = keyword;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKeyword() {
        return keyword;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<ClassHierarchy> getParentHierarchies() {
        return parentHierarchies;
    }

    public void setParentHierarchies(Set<ClassHierarchy> parentHierarchies) {
        this.parentHierarchies = parentHierarchies;
    }

    public Set<ClassHierarchy> getChildHierarchies() {
        return childHierarchies;
    }

    public void setChildHierarchies(Set<ClassHierarchy> childHierarchies) {
        this.childHierarchies = childHierarchies;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
}