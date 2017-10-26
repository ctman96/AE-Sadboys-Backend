package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;



@JsonApiResource(type = "userRoles")
public class UserRoles{
    @JsonApiId
    private long id;
    private long roleId;

    public UserRoles() {
        super();
    }

    public UserRoles(long id, long roleId) {
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

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

}