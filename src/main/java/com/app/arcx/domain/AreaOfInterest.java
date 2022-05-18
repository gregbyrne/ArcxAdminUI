package com.app.arcx.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "area_of_interest")
@NamedQuery(name = "AreaOfInterest.findAll", query="select u from AreaOfInterest u order by u.position ASC")
public class AreaOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String name;

    public int position;

}
