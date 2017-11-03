package com.ipfms.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@Table(name = "records")
@JsonApiResource(type = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonApiId
    private Integer id;
    private String number;
    private String title;
    private Integer scheduleId;
    private Integer typeId;
    private String consignmentCode;
    private Integer stateId;
    private Integer containerId;
    private Integer locationId;
    private Date createdAt;
    private Date updatedAt;
    private Date closedAt;

    public Record() {
        super();
    }

    public Record(Integer id, String number, String title, Integer scheduleId, Integer typeId, String consignmentCode, Integer stateId, Integer containerId, Integer locationId, Date createdAt, Date updatedAt, Date closedAt) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.scheduleId = scheduleId;
        this.typeId = typeId;
        this.consignmentCode = consignmentCode;
        this.stateId = stateId;
        this.containerId = containerId;
        this.locationId = locationId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScheduled() {
        return scheduleId;
    }

    public void setScheduled(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getConsignmentCode() {
        return consignmentCode;
    }

    public void setConsignmentCode(String consignmentCode) {
        this.consignmentCode = consignmentCode;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getContainerId() {
        return containerId;
    }

    public void setContainerId(Integer containerId) {
        this.containerId = containerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }
}