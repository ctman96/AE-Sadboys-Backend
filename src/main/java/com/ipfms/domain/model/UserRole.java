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
    private Integer id;
    private Integer roleId;

    public UserRole() {
        super();
    }

    public UserRole(Integer id, Integer roleId) {
        this.id = id;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}