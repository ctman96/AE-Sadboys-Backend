package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;
import java.util.Date;

@Entity
@JsonApiResource(type = "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private long id;
    private String name;
    private String keyword;
    private Date updatedAt;

    public Classification() {
        super();
    }

    public Classification(long id, String name, String keyword, Date updatedAt) {
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

    public void setId(long id) {
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
