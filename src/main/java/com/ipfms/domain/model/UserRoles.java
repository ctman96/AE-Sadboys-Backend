package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;



@JsonApiResource(type = "userRoles")
public class userRoles{
    @JsonApiId
    private long id;
    private long roleId;

    public userRoles() {
        super();
    }

    public userRoles(long id, long roleId) {
        this.id = id;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}