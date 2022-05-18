package com.app.arcx.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "steps_to_help_prepare_items")
@NamedQuery(name = "StepsToHelpPrepareItems.findAll", query="select u from StepsToHelpPrepareItems u")
public class StepsToHelpPrepareItems {

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
    public Integer aoiId;
    public Integer aoiItemsId;
    public Integer aoiSubItemsId;


    public String name;
    public String content;
    public String subTitle;

}
