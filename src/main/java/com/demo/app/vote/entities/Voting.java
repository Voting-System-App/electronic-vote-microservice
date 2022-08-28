package com.demo.app.vote.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "voting")
@Data
public class Voting {
    @Id
    private String id;
    private List<String> candidate;
    @Field(name = "voting_status")
    private VotingStatus votingStatus;
    @Field(name = "is_active")
    @Enumerated(EnumType.STRING)
    private Boolean isActive;
}
