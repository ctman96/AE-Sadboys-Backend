package com.ipfms.domain.model;

import javax.persistence.*;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@Table(name = "recordtypes")
public class RecordType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String numberPattern;
    private Integer defaultScheduleId;

    public RecordType() {
        super();
    }

    public RecordType(Integer id, String name, String numberPattern, Integer defaultScheduleId) {
        this.id = id;
        this.name = name;
        this.numberPattern = numberPattern;
        this.defaultScheduleId = defaultScheduleId;
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

    public String getNumberPattern() {
        return numberPattern;
    }

    public void setNumberPattern(String numberPattern) {
        this.numberPattern = numberPattern;
    }

    public Integer getDefaultScheduleId() {
        return defaultScheduleId;
    }

    public void setDefaultScheduleId(Integer defaultScheduleId) {
        this.defaultScheduleId = defaultScheduleId;
    }
}