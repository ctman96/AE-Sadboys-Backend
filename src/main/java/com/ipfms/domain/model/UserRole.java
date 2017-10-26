package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "userRoles")
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private long id;
    private long roleId;

    public UserRole() {
        super();
    }

    public UserRole(long id, long roleId) {
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