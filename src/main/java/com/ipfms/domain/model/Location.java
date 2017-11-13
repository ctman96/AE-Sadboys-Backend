package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String code;
    private boolean locked;

    @ManyToMany(mappedBy = "locations")
    private Set<User> users;

    @JsonIgnore
    @OneToMany(targetEntity=Record.class, mappedBy="location", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<Record> records;

    public Location() {
        super();
    }

    public Location(String name, String code, boolean locked) {
        this.name = name;
        this.code = code;
        this.locked = locked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @JsonIgnoreProperties("locations")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
}