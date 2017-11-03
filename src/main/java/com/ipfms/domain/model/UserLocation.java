package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;

@Entity
@JsonApiResource(type = "userLocations")
public class UserLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private Integer id;
    private Integer locationId;

    public UserLocation() {
        super();
    }

    public UserLocation(Integer id, Integer locationId) {
        this.id = id;
        this.locationId = locationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}