package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "recordClassifications")
public class RecordClassifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private int recordId;
    private int classId;
    private int ordinal;

    public RecordClassifications() {
        super();
    }

    public RecordClassifications(int recordId, int classId, int ordinal) {
        this.recordId = recordId;
        this.classId = classId;
        this.ordinal = ordinal;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }
}
