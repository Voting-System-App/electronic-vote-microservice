package com.demo.app.vote.entities.models;

import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class Candidate {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String dni;
    private Boolean gender;
    private String birthDate;
    private PoliticalParty politicalParty;
}
