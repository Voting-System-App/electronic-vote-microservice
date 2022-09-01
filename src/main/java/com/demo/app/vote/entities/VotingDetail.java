package com.demo.app.vote.entities;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "voting_detail")
@Data
public class VotingDetail {
    @Id
    private String id;
    @Field(name = "candidate_id")
    private List<String> candidateId= new ArrayList<>();
    @ManyToOne
    private Voting voting;
}
