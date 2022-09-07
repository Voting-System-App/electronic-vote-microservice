package com.demo.app.vote.entities.models;

import lombok.Data;

@Data
public class City {
    private String id;
    private String name;
    private State state;
}
