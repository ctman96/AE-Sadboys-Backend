package com.ipfms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@IdClass(NotePK.class)
@Table(name = "notes")
public class Note{
    @Id
    private Integer tableId;
    @Id
    private Integer rowId;
    @Id
    private Integer chunk;

    private String text;

    @Formula("(SELECT nt.Name FROM notetables nt WHERE nt.Id = tableId)")
    private String name;

    @ManyToOne(targetEntity = Noted.class)
    @JoinColumn(name="rowId", insertable = false, updatable = false)
    @JsonIgnore
    private Noted noted;

    public Note() {
        super();
    }

    public Note(Integer tableId, Integer rowId, Integer chunk, String text) {
        this.tableId = tableId;
        this.rowId = rowId;
        this.chunk = chunk;
        this.text = text;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Noted getNoted() {
        return noted;
    }

    public void setNoted(Noted noted) {
        this.noted = noted;
    }
}
