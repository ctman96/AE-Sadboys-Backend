package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

import java.util.Date;

@JsonApiResource(type = "classifications")
public class Classifications {
    @JsonApiId
    private int id;
    private String name;
    private String keyword;
    private Date updatedAt;

    public Classifications() {
        super();
    }

    public Classifications(int id, String name, String keyword, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.keyword = keyword;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKeyword() {
        return keyword;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
