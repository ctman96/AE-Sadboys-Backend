package com.ipfms.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "customattributevalues")
public class CustomAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer attrId;
    private Integer recordId;
    private String value;

    public CustomAttributeValue() {
        super();
    }

    public CustomAttributeValue(Integer id, Integer attrId, Integer recordId, String value) {
        this.id = id;
        this.attrId = attrId;
        this.recordId = recordId;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
