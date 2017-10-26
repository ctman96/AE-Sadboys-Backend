package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "recordClassifications")
public class RecordClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private long recordId;
    private long classId;
    private int ordinal;

    public RecordClassification() {
        super();
    }

    public RecordClassification(long recordId, long classId, int ordinal) {
        this.recordId = recordId;
        this.classId = classId;
        this.ordinal = ordinal;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }
}
