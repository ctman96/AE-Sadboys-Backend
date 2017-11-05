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

    @ManyToOne()
    @JoinColumn(name="DefaultScheduleId")
    private RetentionSchedule defaultSchedule;

    public RecordType() {
        super();
    }

    public RecordType(String name, String numberPattern, RetentionSchedule defaultSchedule) {
        this.name = name;
        this.numberPattern = numberPattern;
        this.defaultSchedule = defaultSchedule;
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

    public RetentionSchedule getDefaultSchedule() {
        return defaultSchedule;
    }

    public void setDefaultSchedule(RetentionSchedule defaultSchedule) {
        this.defaultSchedule = defaultSchedule;
    }
}