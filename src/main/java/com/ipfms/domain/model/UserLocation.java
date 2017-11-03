package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;
//TODO: needs to use both ids
@Entity
@Table(name = "userlocations")
@JsonApiResource(type = "userLocations")
public class UserLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private Integer userId;
    private Integer locationId;

    public UserLocation() {
        super();
    }

    public UserLocation(Integer userId, Integer locationId) {
        this.userId = userId;
        this.locationId = locationId;
    }

    public Integer getId() {
        return userId;
    }

    public void setId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}