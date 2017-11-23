package com.ipfms.domain.model;

import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="name")
public abstract class Noted {

    @Id
    private Integer id;

    @IndexedEmbedded
    @OneToMany(mappedBy="noted", fetch=FetchType.LAZY)
    private Set<Note> notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
