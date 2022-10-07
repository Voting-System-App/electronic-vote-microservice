package com.demo.app.vote.entities.models;

import com.bol.secure.Encrypted;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PoliticalParty {
    private String id;
    @Encrypted
    private String description;
    @Encrypted
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date creationDate;
    private Boolean status;
}
