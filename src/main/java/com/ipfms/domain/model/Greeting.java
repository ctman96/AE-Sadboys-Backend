package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

/**
 * Created by Cody on 2017-10-21.
 * THIS IS AN EXAMPLE CLASS
 * See: https://dzone.com/articles/json-api-using-katharsis-amp-spring-boot
 * TODO: DELETE
 */

@JsonApiResource(type = "greetings")
public class Greeting {
    @JsonApiId
    private long id;
    private String content;
    public Greeting() {
        super();
    }
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}