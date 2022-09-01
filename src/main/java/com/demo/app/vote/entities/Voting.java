package com.demo.app.vote.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "voting")
@Data
public class Voting {
    @Id
    private String id;
    private String description;
    @Field(name = "voting_status")
    @Enumerated(EnumType.STRING)
    private VotingStatus votingStatus;
    @OneToOne
    private VotingDate votingDate;
}
