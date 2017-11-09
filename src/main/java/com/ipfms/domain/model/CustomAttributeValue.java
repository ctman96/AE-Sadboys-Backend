package com.ipfms.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "customattributevalues")
public class CustomAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name="AttrId")
    private CustomAttribute attribute;

    @ManyToOne()
    @JoinColumn(name="RecordId")
    private Record record;

    private String value;

    public CustomAttributeValue() {
        super();
    }

    public CustomAttributeValue(CustomAttribute attribute, Record record, String value) {
        this.attribute = attribute;
        this.record = record;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(CustomAttribute attribute) {
        this.attribute = attribute;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
