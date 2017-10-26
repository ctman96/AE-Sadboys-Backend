package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

/**
 * Created by Cody on 2017-10-21.
 */

@JsonApiResource(type = "recordTypes")
public class RecordType {
    @JsonApiId
    private long id;
    private String name;
    private String numberPattern;
    private int defaultScheduled;

    public RecordType() {
        super();
    }

    public RecordType(long id, String name, String numberPattern, int defaultScheduled) {
        this.id = id;
        this.name = name;
        this.numberPattern = numberPattern;
        this.defaultScheduled = defaultScheduled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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