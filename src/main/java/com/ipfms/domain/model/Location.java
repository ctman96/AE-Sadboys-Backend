package com.ipfms.domain.model;

import javax.persistence.*;

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

    public Location() {
        super();
    }

    public Location(Integer id, String name, String code, boolean locked) {
        this.id = id;
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
}