package com.demo.app.vote.entities;

import com.demo.app.vote.entities.models.Candidate;
import jakarta.persistence.*;
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
    @Field(name = "candidate_list")
    @OneToMany
    private List<Candidate> candidateList= new ArrayList<>();
    @ManyToOne
    private Voting voting;
}
