package com.demo.app.vote.entities;

import com.bol.secure.Encrypted;
import com.demo.app.vote.entities.models.Candidate;
import com.demo.app.vote.entities.models.Voter;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Convert;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "voting_detail")
@Data
public class VotingDetail extends Audit<String>{
    @Id
    @Encrypted
    private String id;
    @OneToOne
    private Voter voter;
    @Field(name = "candidate_list")
    @OneToMany
    private List<Candidate> candidateList= new ArrayList<>();
    @ManyToOne
    private Voting voting;
}
