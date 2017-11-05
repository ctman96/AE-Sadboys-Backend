package com.ipfms.domain.model;

import javax.persistence.*;
//TODO: needs to use both ids
@Entity
@Table(name = "userroles")
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private Integer roleId;

    public UserRole() {
        super();
    }

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return userId;
    }

    public void setId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}