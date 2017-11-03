package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@JsonApiResource(type = "recordTypes")
public class RecordType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private Integer id;
    private String name;
    private String numberPattern;
    private int defaultScheduled;

    public RecordType() {
        super();
    }

    public RecordType(Integer id, String name, String numberPattern, int defaultScheduled) {
        this.id = id;
        this.name = name;
        this.numberPattern = numberPattern;
        this.defaultScheduled = defaultScheduled;
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

    public String getNumberPattern() {
        return numberPattern;
    }

    public void setNumberPattern(String numberPattern) {
        this.numberPattern = numberPattern;
    }

    public int getDefaultScheduled() {
        return defaultScheduled;
    }

    public void setDefaultScheduled(int defaultScheduled) {
        this.defaultScheduled = defaultScheduled;
    }
}