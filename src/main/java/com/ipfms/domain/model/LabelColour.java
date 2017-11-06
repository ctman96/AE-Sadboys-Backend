package com.ipfms.domain.model;

import javax.persistence.*;

/**
 * Created by Cody on 2017-10-21.
 */

@Entity
@Table(name = "labelcolours")
public class LabelColour {

    @Id
    private String key;
    private String colour;

    public LabelColour() {
        super();
    }

    public LabelColour(String key, String colour) {
        this.key = key;
        this.colour = colour;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}