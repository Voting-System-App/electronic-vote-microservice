package com.demo.app.vote.entities.models;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Candidate {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String dni;
    private Boolean gender;
    private String birthDate;
    @ManyToOne
    private PoliticalParty politicalParty;
}
