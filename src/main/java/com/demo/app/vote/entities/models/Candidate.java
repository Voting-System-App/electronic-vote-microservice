package com.demo.app.vote.entities.models;

import com.bol.secure.Encrypted;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Convert;
import java.util.Date;

@Data
public class Candidate {
    private String id;
    @Encrypted
    private String name;
    @Encrypted
    private String lastName;
    @Encrypted
    private String email;
    @Encrypted
    private String dni;
    @Encrypted
    private Boolean gender;
    @Encrypted
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private PoliticalParty politicalParty;
    private String votingId;
}
