package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "customAttributeValues")
public class CustomAttributeValues {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private int id;
    private int attrId;
    private int recordId;
    private String value;

    public CustomAttributeValues() {
        super();
    }

    public CustomAttributeValues(int id, int attrId, int recordId, String value) {
        this.id = id;
        this.attrId = attrId;
        this.recordId = recordId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
