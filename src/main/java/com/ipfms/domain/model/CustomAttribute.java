package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customattributes")
public class CustomAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToOne()
    @JoinColumn(name="LookupId")
    private CustomAttributeLookup lookup;

    @OneToMany(targetEntity=CustomAttributeValue.class, mappedBy="attribute", cascade=CascadeType.ALL)
    private Set<CustomAttributeValue> attributeValues;

    public CustomAttribute() {
        super();
    }

    public CustomAttribute(Integer id, String name, CustomAttributeLookup lookup) {
        this.id = id;
        this.name = name;
        this.lookup = lookup;
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

    public CustomAttributeLookup getLookupId() {
        return lookup;
    }

    public void setLookupId(CustomAttributeLookup lookup) {
        this.lookup = lookup;
    }

    @JsonIgnore
    public Set<CustomAttributeValue> getAttributeValues() {
        return attributeValues;
    }

    @JsonIgnore
    public void setAttributeValues(Set<CustomAttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }
}
