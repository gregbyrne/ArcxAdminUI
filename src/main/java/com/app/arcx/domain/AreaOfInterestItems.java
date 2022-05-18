package com.app.arcx.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "area_of_interest_items")
@NamedQuery(name = "AreaOfInterestItems.findAll", query="select u from AreaOfInterestItems u order by u.position ASC")
public class AreaOfInterestItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    private int parentid;

    public String name;
    public String value;

    public int position;

}
