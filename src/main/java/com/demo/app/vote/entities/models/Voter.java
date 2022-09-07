package com.demo.app.vote.entities.models;


import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class Voter {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String dni;
    private String gender;
    private String birthDate;
    @Enumerated(EnumType.STRING)
    private Status isActive;
    private City city;
    private String fingerPrint;
}
