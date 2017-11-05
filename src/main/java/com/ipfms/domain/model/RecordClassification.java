package com.ipfms.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "recordclassifications")
public class RecordClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recordId;
    private Integer classId;
    private int ordinal;

    public RecordClassification() {
        super();
    }

    public RecordClassification(Integer recordId, Integer classId, int ordinal) {
        this.recordId = recordId;
        this.classId = classId;
        this.ordinal = ordinal;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }
}
