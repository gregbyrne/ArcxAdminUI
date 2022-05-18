package com.app.arcx.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@NamedQuery(name = "Regions.findAll", query="select u from Regions u")
@Table(name = "regions")
public class Regions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String name;
    public String code;

}
