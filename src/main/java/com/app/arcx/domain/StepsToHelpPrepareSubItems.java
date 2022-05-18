package com.app.arcx.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "steps_to_help_prepare_sub_items")
public class StepsToHelpPrepareSubItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int parentid;

    public String content;

}
