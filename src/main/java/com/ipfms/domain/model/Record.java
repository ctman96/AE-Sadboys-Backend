package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String number;
    private String title;

    @ManyToOne()
    @JoinColumn(name="ScheduleId")
    private RetentionSchedule schedule;

    @ManyToOne()
    @JoinColumn(name="TypeId")
    private RecordType type;

    private String consignmentCode;

    @ManyToOne()
    @JoinColumn(name="StateId")
    private RecordState state;

    @ManyToOne()
    @JoinColumn(name="ContainerId")
    private Container container;

    @ManyToOne()
    @JoinColumn(name="LocationId")
    private Location location;

    private Date createdAt;
    private Date updatedAt;
    private Date closedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="recordclassifications", joinColumns = @JoinColumn(name="RecordId", referencedColumnName = "Id"), inverseJoinColumns = @JoinColumn(name="ClassId", referencedColumnName = "Id"))
    private Set<Classification> classifications;

    @JsonIgnore
    @OneToMany(targetEntity=CustomAttributeValue.class, mappedBy="record", cascade=CascadeType.ALL)
    private Set<CustomAttributeValue> customAttributeValues;

    public Record() {
        super();
    }

    public Record(String number, String title, RetentionSchedule schedule, RecordType type, String consignmentCode, RecordState state, Container container, Location location, Date createdAt, Date updatedAt, Date closedAt) {
        this.number = number;
        this.title = title;
        this.schedule = schedule;
        this.type = type;
        this.consignmentCode = consignmentCode;
        this.state = state;
        this.container = container;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
    }

    public Integer getId() {
        return id;
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

    public RetentionSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(RetentionSchedule schedule) {
        this.schedule = schedule;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public String getConsignmentCode() {
        return consignmentCode;
    }

    public void setConsignmentCode(String consignmentCode) {
        this.consignmentCode = consignmentCode;
    }

    public RecordState getState() {
        return state;
    }

    public void setState(RecordState state) {
        this.state = state;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Set<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(Set<Classification> classifications) {
        this.classifications = classifications;
    }

    public Set<CustomAttributeValue> getCustomAttributeValues() {
        return customAttributeValues;
    }

    public void setCustomAttributeValues(Set<CustomAttributeValue> customAttributeValues) {
        this.customAttributeValues = customAttributeValues;
    }
}