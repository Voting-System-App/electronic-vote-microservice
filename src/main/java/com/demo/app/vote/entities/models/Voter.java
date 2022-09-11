package com.demo.app.vote.entities.models;


import com.bol.secure.Encrypted;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
    @Encrypted
    private String gender;
    @Encrypted
    private String birthDate;
    @Encrypted
    private String city;
    @Encrypted
    private String fingerPrint;
}
