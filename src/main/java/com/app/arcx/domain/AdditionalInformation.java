package com.app.arcx.domain;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.*;

@Entity
@Data
@Table(name = "additional_information")
@NamedQuery(name = "AdditionalInformation.findAll", query="select u from AdditionalInformation u")
public class AdditionalInformation {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;



    public String description;
    public String regioncode;

}
