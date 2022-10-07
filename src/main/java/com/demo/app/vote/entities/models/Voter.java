package com.demo.app.vote.entities.models;


import com.bol.secure.Encrypted;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class Voter {
    private String id;
    @Encrypted
    private String name;
    @Encrypted
    private String lastName;
    @Encrypted
    private String email;
    @Encrypted
    private String dni;
    private Boolean gender;
    @Encrypted
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    @Encrypted
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date emissionDate;
    @Encrypted
    private String fingerPrint;
    @Encrypted
    private String city;
    @Encrypted
    private String group;
}
