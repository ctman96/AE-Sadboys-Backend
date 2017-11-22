package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customattributes")
public class CustomAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne()
    @JoinColumn(name="LookupId", foreignKey= @ForeignKey(name="fk_customattributeslookup"))
    private CustomAttributeLookup lookup;

    @OneToMany(targetEntity=CustomAttributeValue.class, mappedBy="attribute")
    private Set<CustomAttributeValue> attributeValues;

    public CustomAttribute() {
        super();
    }

    public CustomAttribute(String name, CustomAttributeLookup lookup) {
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

    public CustomAttributeLookup getLookup() {
        return lookup;
    }

    public void setLookup(CustomAttributeLookup lookup) {
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
