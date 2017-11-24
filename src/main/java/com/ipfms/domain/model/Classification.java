package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.ipfms.domain.View;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String keyword;
    @Column(name="UpdatedAt")
    private Date updatedAt;

    @JsonIgnore
    @OneToMany(targetEntity=ClassHierarchy.class, mappedBy="parent", fetch=FetchType.LAZY)
    private Set<ClassHierarchy> parentHierarchies;

    @JsonIgnore
    @OneToMany(targetEntity=ClassHierarchy.class, mappedBy="child", fetch=FetchType.LAZY)
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

    @JsonView(View.Summary.class)
    public Integer getId() {
        return id;
    }

    @JsonView(View.Summary.class)
    public String getName() {
        return name;
    }

    @JsonView(View.Summary.class)
    public String getKeyword() {
        return keyword;
    }

    @JsonView(View.Summary.class)
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