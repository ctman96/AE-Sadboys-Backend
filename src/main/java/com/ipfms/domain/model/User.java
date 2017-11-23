package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    private String firstName;
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="userroles",
            joinColumns = @JoinColumn(name="UserId", referencedColumnName = "Id", foreignKey= @ForeignKey(name="FK_UserRoles_Users")),
            inverseJoinColumns = @JoinColumn(name="RoleId", referencedColumnName = "Id", foreignKey= @ForeignKey(name="FK_UserRoles_Roles")))
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="userlocations",
            joinColumns = @JoinColumn(name="UserId", referencedColumnName = "Id", foreignKey= @ForeignKey(name="FK_UserLocations_Users")),
            inverseJoinColumns = @JoinColumn(name="LocationId", referencedColumnName = "Id", foreignKey= @ForeignKey(name="FK_UserLocations_Locations")))
    private Set<Location> locations;

    public User() {
        super();
    }

    public User(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnoreProperties("users")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnoreProperties("users")
    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    //TODO?
    public Boolean isAdmin(){
        for(Role r : roles){
            if (r.getId() == 1) {
                return true;
            }
        }
        return false;
    }

    public Boolean isRMC(){
        for(Role r : roles){
            if (r.getId() == 1 || r.getId() == 2 ) {
                return true;
            }
        }
        return false;
    }
}