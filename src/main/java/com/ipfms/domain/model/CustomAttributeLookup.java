package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customattributelookups")
public class CustomAttributeLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer setId;
    private String name;

    @OneToMany(targetEntity=CustomAttribute.class, mappedBy="lookup", cascade=CascadeType.ALL)
    private Set<CustomAttribute> customAttributes;

    public CustomAttributeLookup() {
        super();
    }

    public CustomAttributeLookup(Integer setId, String name) {
        this.setId = setId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<CustomAttribute> getCusomAttributes() {
        return customAttributes;
    }

    @JsonIgnore
    public void setCusomAttributes(Set<CustomAttribute> cusomAttributes) {
        this.customAttributes = cusomAttributes;
    }
}
