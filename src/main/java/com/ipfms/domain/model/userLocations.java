package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;



@JsonApiResource(type = "userLocations")
public class userLocations {
    @JsonApiId
    private long id;
    private long locationId;

    public userLocations() {
        super();
    }

    public userLocations(long id, long locationId) {
        this.id = id;
        this.locationId = locationId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }
}