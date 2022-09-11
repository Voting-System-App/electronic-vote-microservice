package com.demo.app.vote.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Document(collection = "voting")
@Data
public class Voting extends Audit{
    @Id
    private String id;
    private String description;
    @Field(name = "voting_status")
    @Enumerated(EnumType.STRING)
    private VotingStatus votingStatus;
    @OneToOne
    private VotingDate votingDate;
    @Field(name = "is_active")
    private Boolean isActive;
}
