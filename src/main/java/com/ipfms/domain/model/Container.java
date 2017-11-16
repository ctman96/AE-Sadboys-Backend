package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@Indexed
@Table(name = "containers")
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Field(index=Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String number;

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String title;

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String consignmentCode;

    private Date createdAt;
    private Date updatedAt;

    @JsonIgnore
    @OneToMany(targetEntity=Record.class, mappedBy="container", cascade=CascadeType.ALL)
    private Set<Record> records;

    public Container() {
        super();
    }

    public Container(String number, String title, String consignmentCode, Date createdAt, Date updatedAt) {
        this.number = number;
        this.title = title;
        this.consignmentCode = consignmentCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getConsignmentCode() {
        return consignmentCode;
    }

    public void setConsignmentCode(String consignmentCode) {
        this.consignmentCode = consignmentCode;
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
}