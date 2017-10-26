package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "customAttributeValues")
public class CustomAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private long id;
    private long attrId;
    private long recordId;
    private String value;

    public CustomAttributeValue() {
        super();
    }

    public CustomAttributeValue(long id, long attrId, long recordId, String value) {
        this.id = id;
        this.attrId = attrId;
        this.recordId = recordId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAttrId() {
        return attrId;
    }

    public void setAttrId(long attrId) {
        this.attrId = attrId;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
