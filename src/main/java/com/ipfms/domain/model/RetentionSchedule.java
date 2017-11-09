package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "retentionschedules")
public class RetentionSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String code;
    private Integer years;

    @JsonIgnore
    @OneToMany(targetEntity=Record.class, mappedBy="schedule", cascade=CascadeType.ALL)
    private Set<Record> records;

    @JsonIgnore
    @OneToMany(targetEntity=RecordType.class, mappedBy="defaultSchedule", cascade=CascadeType.ALL)
    private Set<RecordType> recordTypes;

    public RetentionSchedule(){
        super();
    }

    public RetentionSchedule(String name, String code, Integer years) {
        this.name = name;
        this.code = code;
        this.years = years;
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

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public Set<RecordType> getRecordTypes() {
        return recordTypes;
    }

    public void setRecordTypes(Set<RecordType> recordTypes) {
        this.recordTypes = recordTypes;
    }
}
